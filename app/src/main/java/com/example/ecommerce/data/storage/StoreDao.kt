package com.example.ecommerce.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {

    @Query("SELECT * FROM home_store_table")
    fun readAllData(): Flow<List<ResultItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllResults(resultItemEntity: List<ResultItemEntity>)
}