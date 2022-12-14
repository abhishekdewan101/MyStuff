package com.adewan.mystuff

import android.app.Application
import com.adewan.mystuff.core.di.appModule
import com.adewan.mystuff.core.di.coreModules
import com.adewan.mystuff.feature.di.featureModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
            modules(coreModules)
            modules(featureModules)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
