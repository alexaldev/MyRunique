package com.alexallafi.run.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexallafi.core.database.dao.RunPendingSyncDao
import com.alexallafi.core.domain.run.RemoteRunDataSource
import com.alexallafi.core.domain.run.RunRepository
import com.alexallafi.core.domain.util.Result

class DeleteRunWorker(
    context: Context,
    private val params: WorkerParameters,
    private val remoteRunDataSource: RemoteRunDataSource,
    private val pendingSyncRepository: RunPendingSyncDao
): CoroutineWorker(
    context,
    params
) {
    override suspend fun doWork(): Result {

        if (runAttemptCount >= 5) {
            return Result.failure()
        }

        val runId = params.inputData.getString(RUN_ID) ?: return Result.failure()

        return when(val result = remoteRunDataSource.deleteRun(runId)) {
            is com.alexallafi.core.domain.util.Result.Error -> {
                result.error.toWorkerResult()
            }
            is com.alexallafi.core.domain.util.Result.Success -> {
                pendingSyncRepository.deleteRunPendingSyncEntity(runId)
                Result.success()
            }
        }

    }

    companion object {
        const val RUN_ID = "runId"
    }
}