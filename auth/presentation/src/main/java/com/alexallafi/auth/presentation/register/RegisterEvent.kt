package com.alexallafi.auth.presentation.register

import com.alexallafi.core.presentation.ui.UIText

sealed interface RegisterEvent {

    data object RegistrationSuccess : RegisterEvent

    data class Error(val error: UIText): RegisterEvent
}