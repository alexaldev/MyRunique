package com.alexallafi.run.presentation.di

import com.alexallafi.run.domain.RunningTracker
import com.alexallafi.run.presentation.active_run.ActiveRunViewModel
import com.alexallafi.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runPresentationModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)

    singleOf(::RunningTracker)
}