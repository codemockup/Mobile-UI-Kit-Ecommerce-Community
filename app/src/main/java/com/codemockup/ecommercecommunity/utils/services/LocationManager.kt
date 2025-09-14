package com.codemockup.ecommercecommunity.utils.services

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationServices(
    private val context: Context
) {
    private val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)
    private var callback: LocationCallback? = null
    private var onLocationUpdate: ((Location) -> Unit)? = null

    /**
     * Starts requesting location updates with default interval of 10 seconds
     *
     * @param onUpdate callback function to handle the updated location.
     * @param isContinuous boolean flag to decide whether the updates should be continuous or just a one-time update.
     */
    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun startUpdates(
        onUpdate: (Location) -> Unit,
        interval: Long = 10000,
        isContinuous: Boolean = true
    ) {
        val request = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            interval
        ).build()
        onLocationUpdate = onUpdate

        callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let {
                    onLocationUpdate?.invoke(it)
                    /// stop update if not continuous
                    if (!isContinuous) {
                        stopUpdates()
                    }
                }
            }
        }

        /// check if permission is granted to start updating
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProvider.requestLocationUpdates(
                request,
                callback!!,
                Looper.getMainLooper()
            )
        }
    }

    /// stop location updates
    fun stopUpdates() {
        callback?.let { fusedLocationProvider.removeLocationUpdates(it) }
    }

    /// check if location service is enable
    fun isLocationEnabled(): Boolean {
        val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
}

/// check if permission status is denied
@OptIn(ExperimentalPermissionsApi::class)
fun PermissionStatus.isPermanentlyDenied(context: Context, permission: String): Boolean {
    return this is PermissionStatus.Denied &&
            !ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                permission
            )
}
