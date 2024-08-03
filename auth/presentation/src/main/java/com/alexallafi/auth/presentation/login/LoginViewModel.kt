package com.alexallafi.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alexallafi.auth.domain.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Suppress("OPT_IN_USAGE_FUTURE_ERROR")
class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent> {  }
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {

    }
}