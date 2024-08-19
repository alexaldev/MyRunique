package com.alexallafi.data.di

import com.alexallafi.analytics.domain.AnalyticsRepository
import com.alexallafi.data.RoomAnalyticsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository) bind AnalyticsRepository::class
}