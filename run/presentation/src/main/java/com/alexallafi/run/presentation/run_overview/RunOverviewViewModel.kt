package com.alexallafi.run.presentation.run_overview

import androidx.lifecycle.ViewModel

class RunOverviewViewModel: ViewModel() {
    fun onAction(action: RunOverviewAction) {
        when(action) {
            RunOverviewAction.OnAnalyticsClick -> TODO()
            RunOverviewAction.OnLogoutClick -> TODO()
            RunOverviewAction.OnStartRun -> TODO()
        }
    }
}