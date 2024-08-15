package com.alexallafi.core.database

import android.database.sqlite.SQLiteFullException
import com.alexallafi.core.database.dao.RunDao
import com.alexallafi.core.database.mappers.toRun
import com.alexallafi.core.database.mappers.toRunEntity
import com.alexallafi.core.domain.run.LocalRunDataSource
import com.alexallafi.core.domain.run.Run
import com.alexallafi.core.domain.run.RunId
import com.alexallafi.core.domain.util.DataError
import com.alexallafi.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalRunDataSource(
    private val runDao: RunDao
): LocalRunDataSource {
    override fun getRuns(): Flow<List<Run>> {
        return runDao
            .getRuns()
            .map { runEntities ->
                runEntities.map { it.toRun() }
            }
    }

    override suspend fun upsertRun(run: Run): Result<RunId, DataError.Local> {
        return try {
            val runEntity = run.toRunEntity()
            runDao.upsertRun(runEntity)
            Result.Success(runEntity.id)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun upsertRuns(runs: List<Run>): Result<List<RunId>, DataError.Local> {
        return try {
            val runEntities = runs.map { it.toRunEntity() }
            runDao.upsertRuns(runEntities)
            Result.Success(runEntities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteRun(id: RunId) {
        runDao.deleteRun(id)
    }

    override suspend fun deleteAll() {
        runDao.deleteAll()
    }
}