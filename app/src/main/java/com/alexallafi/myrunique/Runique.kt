package com.alexallafi.myrunique

import android.app.Application
import android.content.Context
import com.alexallafi.auth.data.di.authDataModule
import com.alexallafi.auth.presentation.di.authViewModelModule
import com.alexallafi.run.presentation.di.runPresentationModule
import com.alexallafi.core.data.di.coreDataModule
import com.alexallafi.core.database.di.databaseModule
import com.alexallafi.myrunique.di.appModule
import com.alexallafi.run.data.di.runDataModule
import com.alexallafi.run.location.di.locationModule
import com.alexallafi.run.network.di.networkModule
import com.google.android.play.core.splitcompat.SplitCompat
import com.plcoding.runique.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
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
            workManagerFactory()
            modules(
                appModule,
                authDataModule,
                authViewModelModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}