package com.alexallafi.auth.domain

data class PasswordValidationState(
    val hasMinimumLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseCharacter: Boolean = false,
    val hasUpperCaseCharacter: Boolean = false
) {
    val isValidPassword: Boolean
        get() = hasNumber && hasMinimumLength && hasLowerCaseCharacter && hasUpperCaseCharacter
}