package com.alexallafi.data

import com.alexallafi.analytics.domain.AnalyticsValues
import com.alexallafi.analytics.domain.AnalyticsRepository
import com.alexallafi.core.database.dao.AnalyticsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class RoomAnalyticsRepository(
    private val analyticsDao: AnalyticsDao
): AnalyticsRepository {
    override suspend fun getAnalyticsValues(): AnalyticsValues {

        return withContext(Dispatchers.IO) {

            val totalDistance = async {  analyticsDao.getTotalDistance() }
            val totalTimeRun = async {  analyticsDao.getTotalTimeRun() }
            val fastestEverRun = async {  analyticsDao.getMaxRunSpeed() }
            val avgDistance = async {  analyticsDao.getAvgDistancePerRun() }
            val avgPace = async {  analyticsDao.getAvgPacePerRun() }

            AnalyticsValues(
                totalDistanceRun = totalDistance.await(),
                totalTimeRun = totalTimeRun.await().milliseconds,
                fastestEverRun = fastestEverRun.await(),
                avgDistance = avgDistance.await(),
            )
        }
    }
}