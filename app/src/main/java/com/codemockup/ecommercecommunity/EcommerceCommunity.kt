package com.codemockup.ecommercecommunity

import android.app.Application
import com.codemockup.ecommercecommunity.di.appModules
import com.codemockup.ecommercecommunity.utils.sentry.SentryService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EcommerceCommunity : Application() {
    override fun onCreate() {
        super.onCreate()

        /// initialize Sentry
        SentryService.init(
            context = this,
            isEnable = false
        )

        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(this@EcommerceCommunity)
                androidLogger(Level.DEBUG)
                modules(appModules)
            }
        }
    }
}