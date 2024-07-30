

package com.alexallafi.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexallafi.auth.domain.AuthRepository
import com.alexallafi.auth.domain.UserDataValidator
import com.alexallafi.core.domain.util.DataError
import com.alexallafi.core.domain.util.Result
import com.alexallafi.core.presentation.ui.UIText
import com.alexallafi.core.presentation.ui.asUiText
import com.plcoding.auth.presentation.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Suppress("OPT_IN_USAGE_FUTURE_ERROR")
class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow()
            .onEach { email ->

                val isValidEmail = userDataValidator.isValidEmail(email.toString())

                state = state.copy(
                    isEmailValid = isValidEmail,
                    canRegister = isValidEmail && state.passwordValidationState.isValidPassword
                            && !state.isRegistering
                )

            }
            .launchIn(viewModelScope)

        state.password.textAsFlow()
            .onEach { password ->

                val passwordValidationState = userDataValidator.validatePassword(password.toString())

                state = state.copy(
                    passwordValidationState = passwordValidationState,
                    canRegister = state.isEmailValid && passwordValidationState.isValidPassword
                            && !state.isRegistering
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(registerAction: RegisterAction) {
        when(registerAction) {
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = state.isPasswordVisible.not()
                )
            }
            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {

            state = state.copy(isRegistering = true)

            val result = authRepository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )

            state = state.copy(isRegistering = false)

            when(result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(RegisterEvent.Error(
                            UIText.StringResource(R.string.error_email_exists)
                        ))
                    } else {
                        eventChannel.send(RegisterEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}