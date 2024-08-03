package com.alexallafi.auth.presentation.di

import com.alexallafi.auth.presentation.login.LoginViewModel
import com.alexallafi.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}