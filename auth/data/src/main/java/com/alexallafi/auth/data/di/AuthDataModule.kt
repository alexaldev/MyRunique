package com.alexallafi.auth.data.di

import com.alexallafi.auth.data.EmailPatternValidator
import com.alexallafi.auth.domain.PatternValidator
import com.alexallafi.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {

    single<PatternValidator> {
        EmailPatternValidator
    }

    singleOf(::UserDataValidator)
}