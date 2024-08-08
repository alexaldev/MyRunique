package com.alexallafi.myrunique

import android.app.Application
import com.alexallafi.auth.data.di.authDataModule
import com.alexallafi.auth.presentation.di.authViewModelModule
import com.alexallafi.run.presentation.di.runPresentationModule
import com.alexallafi.core.data.di.coreDataModule
import com.alexallafi.myrunique.di.appModule
import com.alexallafi.run.location.di.locationModule
import com.plcoding.runique.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class Runique : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

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
                authViewModelModule,
                coreDataModule,
                runPresentationModule,
                locationModule
            )
        }
    }
}