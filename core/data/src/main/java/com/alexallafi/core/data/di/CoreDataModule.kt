package com.alexallafi.core.data.di

import com.alexallafi.core.data.auth.EncryptedSessionStorage
import com.alexallafi.core.data.networking.HttpClientFactory
import com.alexallafi.core.data.run.OfflineFirstRunRepository
import com.alexallafi.core.domain.SessionStorage
import com.alexallafi.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }

    singleOf(::EncryptedSessionStorage) bind SessionStorage::class
    singleOf(::OfflineFirstRunRepository) bind RunRepository::class
}