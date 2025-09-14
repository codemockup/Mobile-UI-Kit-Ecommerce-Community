package com.codemockup.ecommercecommunity.features.shared.components

import android.Manifest
import android.location.Location
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.codemockup.ecommercecommunity.common.constants.SharedPreferenceKeys
import com.codemockup.ecommercecommunity.utils.SharedPreference
import com.codemockup.ecommercecommunity.utils.helper.ExplicitIntentHelper
import com.codemockup.ecommercecommunity.utils.services.LocationServices
import com.codemockup.ecommercecommunity.utils.services.isPermanentlyDenied
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationHandler(
    continous: Boolean = false,
    locationServices: LocationServices = koinInject(),
    onLocationUpdate: (Location) -> Unit,
    onPermissionDenied: () -> Unit = {},
    onLocationServicesOff: () -> Unit = {},
    content: @Composable () -> Unit
) {
    /// context
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    /// hold all necessary permission
    val locationPermissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val multiplePermissionsState = rememberMultiplePermissionsState(locationPermissions)
    /// check wether permission has been requested
    var hasRequestedPermission by remember { mutableStateOf(false) }

    /// show permission denied alert
    var showPermissionDialog by remember { mutableStateOf(false) }
    /// show location service off alert
    var showLocationOffDialog by remember { mutableStateOf(false) }

    var locationUpdateJob: Job? = remember { null }

    /// check if user already prompted atleast once for permission request
    fun isUserPromptedPermission(): Boolean {
        return SharedPreference.getPrimitiveData(
            SharedPreferenceKeys.LOCATION_PERMISSION_KEY,
            false
        )
    }

    /**
     * check location permission and location service
     * request permission and show dialog if user denied the permission
     */
    fun checkPermissionAndLocationStatus() {
        /// check if one of fine / coarse is granted
        val isAnyLocationPermissionGranted =
            multiplePermissionsState.permissions.any { it.status.isGranted }

        if (isAnyLocationPermissionGranted) {
            /// granted

            /// check if location service is active or not
            if (!locationServices.isLocationEnabled()) {
                /// show location service off alert
                locationUpdateJob?.start()
                showLocationOffDialog = true
                onLocationServicesOff()
            }

        } else {
            /// no permission granted

            /// check if one of the permission is denied
            /// will return false if user not yet accept or denied
            val isPermissionDenied = multiplePermissionsState.permissions.any {
                it.status.isPermanentlyDenied(context, it.permission)
            }

            /// check if user already being requested
            if (!hasRequestedPermission && (isUserPromptedPermission() != isPermissionDenied)) {
                hasRequestedPermission = true
                /// show location request prompt
                multiplePermissionsState.launchMultiplePermissionRequest()
                /// save to shared that user already prompted with permission
                SharedPreference.savePrimitiveData(
                    SharedPreferenceKeys.LOCATION_PERMISSION_KEY,
                    true
                )
            } else if (multiplePermissionsState.shouldShowRationale || isPermissionDenied) {
                /// already denied permission

                /// show permission denied alert
                showPermissionDialog = true
                onPermissionDenied()
            }
        }
    }

    /// check permission state
    LaunchedEffect(multiplePermissionsState.permissions.map { it.status }) {
        checkPermissionAndLocationStatus()
    }

    /// listen for location service every seconds using job for cancelation
    LaunchedEffect(Unit) {
        locationUpdateJob = coroutineScope.launch {
            while (true) {
                /// check if location service is active
                val enabled = locationServices.isLocationEnabled()
                /// check if one of fine / coarse is granted
                val isAnyLocationPermissionGranted =
                    multiplePermissionsState.permissions.any { it.status.isGranted }
                if (enabled) {
                    showLocationOffDialog = false

                    if (isAnyLocationPermissionGranted) {
                        locationServices.startUpdates(onLocationUpdate, isContinuous = continous)

                        // Stop the loop as we already get the location
                        locationUpdateJob?.cancel()
                        break
                    }
                } else if (isAnyLocationPermissionGranted) {
                    showLocationOffDialog = true
                    onLocationServicesOff()
                    locationServices.stopUpdates()
                }

                delay(1000) // Check every second
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && isUserPromptedPermission()) {
                /// re-check if app return to foreground
                checkPermissionAndLocationStatus()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        /// cleanup
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    /// stop location update on exist
    DisposableEffect(Unit) {
        onDispose {
            locationUpdateJob?.cancel()
            locationServices.stopUpdates()
        }
    }

    /// actual content
    content()

    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Location Permission Denied") },
            text = { Text("This app needs location access to function correctly.") },
            confirmButton = {
                TextButton(onClick = {
                    showPermissionDialog = false
                    ExplicitIntentHelper.navigateToAppSettings(context)
                }) {
                    Text("Open Settings")
                }
            }
        )
    }

    if (showLocationOffDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Enable Location") },
            text = { Text("Location services are disabled. Please turn them on.") },
            confirmButton = {
                TextButton(onClick = {
                    showLocationOffDialog = false
                    ExplicitIntentHelper.navigateToLocation(context)
                }) {
                    Text("Open Settings")
                }
            }
        )
    }
}
