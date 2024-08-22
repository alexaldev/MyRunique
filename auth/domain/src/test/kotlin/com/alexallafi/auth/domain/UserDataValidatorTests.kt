package com.alexallafi.auth.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.math.exp

class UserDataValidatorTests {

    private lateinit var testValidator: UserDataValidator

    @BeforeEach
    fun setup() {
        testValidator = UserDataValidator(
            patternValidator = object : PatternValidator {
                override fun matches(value: String): Boolean {
                    return true
                }
            }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "Test12345, true",
        "test12345, false",
        "12345, false",
        "Test-1234, true",
        "TEST12345, false"
    )
    fun testValidatePassword(password: String, expectedIsValid: Boolean) {

        val state = testValidator.validatePassword(password)

        assertThat(state.isValidPassword).isEqualTo(expectedIsValid)
    }
}