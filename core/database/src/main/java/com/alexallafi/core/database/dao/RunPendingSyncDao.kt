package com.alexallafi.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.alexallafi.core.database.entity.DeletedRunSyncEntity
import com.alexallafi.core.database.entity.RunPendingSyncEntity

@Dao
interface RunPendingSyncDao {

    @Query("SELECT * FROM RunPendingSyncEntity WHERE userId = :userId")
    suspend fun getAllRunPendingSyncEntities(userId: String): List<RunPendingSyncEntity>

    @Query("SELECT * FROM RunPendingSyncEntity WHERE id = :runId")
    suspend fun getRunPendingSyncEntity(runId: String): RunPendingSyncEntity?

    @Upsert
    suspend fun upsertRunPendingSyncEntity(runPendingSyncEntity: RunPendingSyncEntity)



    @Query("DELETE FROM RunPendingSyncEntity WHERE id = :runId")
    suspend fun deleteRunPendingSyncEntity(runId: String)

    @Query("SELECT * FROM RunPendingSyncEntity WHERE id = :userId")
    suspend fun getAllDeletedRunSyncEntities(userId: String): List<DeletedRunSyncEntity>

    @Upsert
    suspend fun upsertDeletedRunSyncEntity(deletedRunSyncEntity: DeletedRunSyncEntity)

    @Query("DELETE FROM DeletedRunSyncEntity WHERE runId = :runId")
    suspend fun deleteDeletedRunSyncEntity(runId: String)
}