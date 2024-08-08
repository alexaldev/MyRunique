package com.alexallafi.run.location

import android.location.Location
import com.alexallafi.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.alexallafi.core.domain.location.Location(
            this.latitude,
            this.longitude
        ),
        altitude = this.altitude
    )
}