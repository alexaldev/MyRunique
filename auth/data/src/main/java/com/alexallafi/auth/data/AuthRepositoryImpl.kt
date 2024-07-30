package com.alexallafi.auth.data

import com.alexallafi.auth.domain.AuthRepository
import com.alexallafi.core.data.networking.post
import com.alexallafi.core.domain.util.DataError
import com.alexallafi.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
) : AuthRepository {

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email,
                password
            )
        )
    }
}