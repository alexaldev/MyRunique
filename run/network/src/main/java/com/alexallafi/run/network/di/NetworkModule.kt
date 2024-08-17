package com.alexallafi.run.network.di

import com.alexallafi.core.domain.run.RemoteRunDataSource
import com.alexallafi.run.network.KtorRemoteRunDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.single

val networkModule = module {

    singleOf(::KtorRemoteRunDataSource) bind RemoteRunDataSource::class
}