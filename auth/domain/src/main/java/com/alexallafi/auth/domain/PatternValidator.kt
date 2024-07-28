package com.alexallafi.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}