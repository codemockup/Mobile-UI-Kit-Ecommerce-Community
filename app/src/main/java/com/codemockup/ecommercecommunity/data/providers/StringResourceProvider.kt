package com.codemockup.ecommercecommunity.data.providers

import androidx.annotation.StringRes

interface StringResourceProvider {
    // Generic method for any string resource
    fun getString(@StringRes stringResId: Int): String
    fun getString(@StringRes stringResId: Int, vararg formatArgs: Any): String

    // Specific network error messages
    fun getRequestTimedOutMessage(): String
    fun getNoInternetMessage(): String
    fun getNetworkErrorMessage(): String
    fun getUnknownErrorMessage(): String
}