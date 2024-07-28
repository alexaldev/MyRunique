package com.alexallafi.myrunique

import android.app.Application
import com.alexallafi.auth.data.di.authDataModule
import com.alexallafi.auth.presentation.di.authViewModelModule
import com.alexallafi.myrunique.di.appModule
import com.plcoding.runique.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class Runique : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@Runique)
            modules(
                appModule,
                authDataModule,
                authViewModelModule
            )
        }
    }
}