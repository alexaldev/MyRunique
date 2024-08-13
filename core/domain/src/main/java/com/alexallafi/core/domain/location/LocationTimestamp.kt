package com.alexallafi.core.domain.location

import kotlin.time.Duration

data class LocationTimestamp(
    val location: LocationWithAltitude,
    val durationTimestamp: Duration
)