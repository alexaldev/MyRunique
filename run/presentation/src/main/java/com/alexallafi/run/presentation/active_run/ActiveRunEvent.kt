package com.alexallafi.run.presentation.active_run

import com.alexallafi.core.presentation.ui.UIText

sealed interface ActiveRunEvent {
    data class Error(val message: UIText) : ActiveRunEvent
    data object RunSaved : ActiveRunEvent

}