package com.example.ecommerce.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultItemDao {

    @Query("SELECT * FROM home_shop_table")
    fun readAllData(): Flow<List<ResultItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResult(resultItemEntity: List<ResultItemEntity>)
}