package com.alexallafi.auth.domain

import com.alexallafi.core.domain.util.DataError
import com.alexallafi.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(
        email: String,
        password: String
    ): EmptyResult<DataError.Network>

    suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network>
}