package com.alexallafi.run.domain

import com.alexallafi.core.domain.location.LocationWithAltitude
import kotlinx.coroutines.flow.Flow

interface LocationObserver {
    fun observeLocation(
        interval: Long
    ): Flow<LocationWithAltitude>
}