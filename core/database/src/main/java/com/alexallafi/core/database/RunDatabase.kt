package com.alexallafi.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexallafi.core.database.dao.RunDao
import com.alexallafi.core.database.dao.RunPendingSyncDao
import com.alexallafi.core.database.entity.DeletedRunSyncEntity
import com.alexallafi.core.database.entity.RunEntity
import com.alexallafi.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
               ],
    version = 1
)
abstract class RunDatabase: RoomDatabase() {

    abstract val runDao: RunDao

    abstract val runPendingSyncDao: RunPendingSyncDao
}