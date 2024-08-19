package com.alexallafi.analytics.presentation

import com.alexallafi.analytics.domain.AnalyticsValues
import com.alexallafi.core.presentation.ui.formatted
import com.alexallafi.core.presentation.ui.toFormattedKm
import com.alexallafi.core.presentation.ui.toFormattedKmh
import com.alexallafi.core.presentation.ui.toFormattedMeters
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

fun Duration.toFormattedTotalTime(): String {
    val days = this.toLong(DurationUnit.DAYS)
    val hours = this.toLong(DurationUnit.HOURS) % 24
    val minutes = this.toLong(DurationUnit.MINUTES) % 60

    return "${days}d ${hours}h ${minutes}m"
}

fun AnalyticsValues.toAnalyticsDashboardState(): AnalyticsDashboardState {
    return AnalyticsDashboardState(
        totalDistanceRun = (totalDistanceRun / 1000.0).toFormattedKm(),
        totalTimeRun = totalTimeRun.toFormattedTotalTime(),
        fastestEverRun = fastestEverRun.toFormattedKmh(),
        avgDistance = (avgDistance / 1000.0).toFormattedKm(),
        avgPace = avgPace.seconds.formatted()
    )
}