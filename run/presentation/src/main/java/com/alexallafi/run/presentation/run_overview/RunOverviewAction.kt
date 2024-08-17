package com.alexallafi.run.presentation.run_overview

import com.alexallafi.core.domain.run.RunId
import com.alexallafi.run.presentation.run_overview.model.RunUi

sealed interface RunOverviewAction {
    data object OnStartRun: RunOverviewAction
    data object OnLogoutClick:  RunOverviewAction
    data object OnAnalyticsClick: RunOverviewAction
    data class DeleteRun(val run: RunUi): RunOverviewAction
}