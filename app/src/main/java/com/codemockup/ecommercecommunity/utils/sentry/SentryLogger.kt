package com.codemockup.ecommercecommunity.utils.sentry

import io.sentry.Breadcrumb
import io.sentry.Sentry
import io.sentry.SentryEvent
import io.sentry.SentryLevel
import io.sentry.protocol.Message

object SentryLogger {
    /// create new log event to sentry
    fun logEvent(
        breadcrumbCategory: String,
        breadcrumbMessage: String,
        breadcrumbLevel: CoreSentryLevel,
        eventMessage: String,
    ) {
        /// add sentry breadcrumb for tracing
        Sentry.addBreadcrumb(
            Breadcrumb().apply {
                category = breadcrumbCategory
                message = breadcrumbMessage
                level = when (breadcrumbLevel) {
                    CoreSentryLevel.DEBUG -> SentryLevel.DEBUG
                    CoreSentryLevel.INFO -> SentryLevel.INFO
                    CoreSentryLevel.WARNING -> SentryLevel.WARNING
                    CoreSentryLevel.ERROR -> SentryLevel.ERROR
                    CoreSentryLevel.FATAL -> SentryLevel.FATAL
                }
            }
        )
        val event = SentryEvent().apply {
            this.level = SentryLevel.FATAL
            message = Message().apply {
                message = eventMessage
            }
        }
        Sentry.captureEvent(event)
    }

    /// add api breadcrumb to sentry
    fun apiBreadcrumb(
        url: String,
        method: String,
        statusCode: String,
        hasToken: Boolean,
    ) {
        Sentry.addBreadcrumb(Breadcrumb().apply {
            category = "api.request"
            message = "${method.uppercase()} $url [$statusCode]"
            level = SentryLevel.INFO
            setData("URL", url)
            setData("Method", method)
            setData("With Token", hasToken)
        })
    }

    /// add custom context to sentry
    fun customContext(
        title: String,
        content: Map<String, String>,
        exceptionError: Exception,
    ) {
        Sentry.withScope { scope ->
            scope.setContexts(title, content)
            Sentry.captureException(exceptionError)
        }
    }
}