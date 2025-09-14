package com.codemockup.ecommercecommunity.utils.sentry

import android.content.Context
import com.codemockup.ecommercecommunity.BuildConfig
import io.sentry.SentryOptions
import io.sentry.android.core.SentryAndroid
import java.io.IOException
import java.net.SocketException

object SentryService {

    /// initialize sentry service
    fun init(
        context: Context,
        isEnable: Boolean,
        enableNDK: Boolean = false,
        traceSampleRate: Double = 1.0,
        attachScreenShot: Boolean = true,
        traceOptionRequest: Boolean = false
    ) {
        SentryAndroid.init(context) { options ->
            options.dsn = BuildConfig.SENTRY_DSN
            options.isDebug = BuildConfig.DEBUG
            options.isEnabled = isEnable // enable / disable reporting
            options.isEnableNdk = enableNDK // disable native sdk error reporting
            options.environment = BuildConfig.FLAVOR // sentry envy
            options.isAttachScreenshot = attachScreenShot
            options.tracesSampleRate = traceSampleRate
            options.isTraceOptionsRequests = traceOptionRequest
            options.beforeSend = SentryOptions.BeforeSendCallback { event, _ ->
                /// disable network RTO / no connection error
                val throwable = event.throwable
                if (throwable != null && isNoInternetException(throwable)) {
                    null
                } else {
                    event
                }
            }
        }
    }

    /**
     * check if throwable is network related error RTO/no internet connection
     */
    private fun isNoInternetException(throwable: Throwable): Boolean {
        return when (throwable) {
            is java.net.ConnectException,
            is java.net.SocketTimeoutException,
            is java.net.UnknownHostException,
            is SocketException,
            is IOException -> true

            else -> {
                throwable.cause?.let { isNoInternetException(it) } == true
            }
        }
    }
}