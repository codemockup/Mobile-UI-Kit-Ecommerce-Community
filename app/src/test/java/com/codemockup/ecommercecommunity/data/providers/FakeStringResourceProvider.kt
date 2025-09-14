package com.codemockup.ecommercecommunity.data.providers

import androidx.annotation.StringRes

class FakeStringResourceProvider : StringResourceProvider {
    private val strings = mutableMapOf<Int, String>()

    fun setString(@StringRes stringResId: Int, value: String) {
        strings[stringResId] = value
    }

    override fun getString(@StringRes stringResId: Int): String {
        return strings[stringResId] ?: "Mock string for ID: $stringResId"
    }

    override fun getString(@StringRes stringResId: Int, vararg formatArgs: Any): String {
        val template = getString(stringResId)
        return String.format(template, *formatArgs)
    }

    override fun getRequestTimedOutMessage(): String = "Request timed out"
    override fun getNoInternetMessage(): String = "No internet"
    override fun getNetworkErrorMessage(): String = "Network error"
    override fun getUnknownErrorMessage(): String = "Unknown error"
}