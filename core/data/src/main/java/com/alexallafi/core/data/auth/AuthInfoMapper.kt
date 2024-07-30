package com.alexallafi.core.data.auth

import com.alexallafi.core.domain.AuthInfo

fun AuthInfo.toAuthInfoSerializable() = AuthInfoSerializable(
    accessToken, refreshToken, userId
)

fun AuthInfoSerializable.toAuthInfo() = AuthInfo(
    accessToken, refreshToken, userId
)