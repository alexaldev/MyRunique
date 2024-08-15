package com.alexallafi.core.domain.run

import com.alexallafi.core.domain.util.DataError
import com.alexallafi.core.domain.util.EmptyResult
import com.alexallafi.core.domain.util.Result

interface RemoteRunDataSource {
    suspend fun getRuns(): Result<List<Run>, DataError.Network>
    suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network>
    suspend fun deleteRun(id: RunId): EmptyResult<DataError.Network>

}