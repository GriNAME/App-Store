package com.example.details_api.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.details_api.data.storage.model.DetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailsDao {

    @Query("SELECT * FROM details_table")
    fun getDetails(): Flow<DetailsEntity>

    @Insert(onConflict = IGNORE)
    suspend fun insertDetails(detailsEntity: DetailsEntity)
}