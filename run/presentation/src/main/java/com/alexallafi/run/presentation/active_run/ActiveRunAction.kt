package com.alexallafi.run.presentation.active_run

sealed interface ActiveRunAction {
    data object OnToggleRunClick: ActiveRunAction
    data object OnStopRunClick: ActiveRunAction
    data object OnResumeRunClick : ActiveRunAction
    data object OnBackClick : ActiveRunAction
    data object OnDismissDialog: ActiveRunAction
    data class SubmitLocationPermissionInfo(
        val acceptedLocationPermission: Boolean,
        val showLocationRationale: Boolean
    ): ActiveRunAction

    data class SubmitNotificationPermissionInfo(
        val acceptedNotificationPermission: Boolean,
        val showNotificationRationale: Boolean
    ): ActiveRunAction
    data object DimissRationaleDialog : ActiveRunAction
}