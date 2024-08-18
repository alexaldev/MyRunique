package com.alexallafi.run.data.di

import com.alexallafi.run.data.CreateRunWorker
import com.alexallafi.run.data.DeleteRunWorker
import com.alexallafi.run.data.FetchRunsWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

}