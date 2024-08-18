package com.alexallafi.run.data.di

import com.alexallafi.core.domain.run.SyncRunScheduler
import com.alexallafi.run.data.CreateRunWorker
import com.alexallafi.run.data.DeleteRunWorker
import com.alexallafi.run.data.FetchRunsWorker
import com.alexallafi.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler) bind SyncRunScheduler::class
}