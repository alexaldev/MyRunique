package com.alexallafi.run.presentation.active_run

import com.alexallafi.core.domain.location.Location
import com.alexallafi.run.domain.RunData
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class ActiveRunState(
    val elapsedTime: Duration = Duration.ZERO,
    val runData: RunData = RunData(),
    val shouldTrack: Boolean = false,
    val hasStartedRunning: Boolean = false,
    val currentLocation: Location? = null,
    val isRunFinished: Boolean = false,
    val isSavingRun: Boolean = false
)
