package com.alexallafi.core.domain


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toDuration

object Timer {

    fun timeAndEmit(): Flow<Duration> {
        return flow {
            var lastEmitTime = System.currentTimeMillis()
            while(true) {
                delay(200L)
                val currentTIme = System.currentTimeMillis()
                val elapsedTime = currentTIme - lastEmitTime
                emit(elapsedTime.milliseconds)
                lastEmitTime = currentTIme
            }
        }
    }
}