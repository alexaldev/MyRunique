package com.alexallafi.auth.presentation.login

import com.alexallafi.core.presentation.ui.UIText

sealed interface LoginEvent {
    data class Error(val error: UIText): LoginEvent
    data object LoginSuccess: LoginEvent
}