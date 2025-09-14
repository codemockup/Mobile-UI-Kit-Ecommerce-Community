package com.codemockup.ecommercecommunity.utils.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast

object ExplicitIntentHelper {

    /**
     * Navigates the user to the app's settings page where they can modify app permissions.
     *
     * @param context The context used to start the settings activity.
     */
    fun navigateToAppSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Unable to open app settings", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Navigates the user to the app's settings page where they can turn on location.
     *
     * @param context The context used to start the settings activity.
     */
    fun navigateToLocation(context: Context) {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }
}