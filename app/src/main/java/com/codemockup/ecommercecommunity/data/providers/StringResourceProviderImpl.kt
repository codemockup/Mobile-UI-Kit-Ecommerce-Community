package com.codemockup.ecommercecommunity.data.providers

import android.content.Context
import androidx.annotation.StringRes
import com.codemockup.ecommercecommunity.R

class StringResourceProviderImpl(private val context: Context) : StringResourceProvider {

    override fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    override fun getString(@StringRes stringResId: Int, vararg formatArgs: Any): String {
        return context.getString(stringResId, *formatArgs)
    }

    override fun getRequestTimedOutMessage(): String {
        return getString(R.string.request_timed_out_please_try_again)
    }

    override fun getNoInternetMessage(): String {
        return getString(R.string.no_internet_connection_please_check_your_network_and_try_again)
    }

    override fun getNetworkErrorMessage(): String {
        return getString(R.string.network_error)
    }

    override fun getUnknownErrorMessage(): String {
        return getString(R.string.an_unknown_error_occurred)
    }
}