package com.example.details_api.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.details_api.data.storage.model.*
import kotlinx.coroutines.flow.Flow


@Dao
interface DetailsDao {

    @Query("SELECT * FROM details")
    fun getDetails(): Flow<DetailsEntity>

    @Insert(onConflict = IGNORE)
    suspend fun insertDetails(detailsEntity: DetailsEntity)
}