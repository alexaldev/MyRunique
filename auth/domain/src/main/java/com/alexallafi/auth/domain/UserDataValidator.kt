package com.alexallafi.auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email.trim())
    }

    fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasUppercase = password.any { it.isUpperCase() }

        return PasswordValidationState(
            hasMinimumLength = hasMinLength,
            hasUpperCaseCharacter = hasUppercase,
            hasLowerCaseCharacter = hasLowercase,
            hasNumber = hasDigit
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}