package com.codemockup.ecommercecommunity.utils.helper

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import com.codemockup.ecommercecommunity.common.constants.NavigationConst

object NavigationHelper {
    /**
     * Parse arguments bundle to Object
     *  - Decode Uri safe encoded string args to Object
     */
    inline fun <reified T : Any> NavBackStackEntry?.parseArgs(): T? {
        val args = this?.arguments?.getString(NavigationConst.DEFAULT_ARGS)?.let {
            val uriDecoded = Uri.decode(it)
            Logger.i("beanDetailArgs: $uriDecoded")
            TypeConverters.toObject(uriDecoded, T::class.java)
        }
        return args
    }

    /**
     * Create route with arguments
     *  - Encode string args (JSON format) into Uri safe format
     *
     * @param route route name
     * @param args arguments to pass
     * @return route with arguments
     */
    fun createRouteWithArgs(route: String, args: String?): String {
        val uriEncoded = Uri.encode(args)
        return "$route?${NavigationConst.DEFAULT_ARGS}=$uriEncoded"
    }

}