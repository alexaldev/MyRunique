package com.alexallafi.run.location.di

import com.alexallafi.run.domain.LocationObserver
import com.alexallafi.run.location.AndroidLocationObserver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {
    singleOf(::AndroidLocationObserver).bind<LocationObserver>()
}