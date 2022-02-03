package com.example.ecommerce.data.storage

import androidx.room.*
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.HotSalesEntity
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.data.storage.entity.relation.ResultWithBestSellers
import com.example.ecommerce.data.storage.entity.relation.ResultWithHotSales
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@Dao
interface StoreDao {

    @Query("SELECT * FROM home_store_table")
    fun readAllData(): Flow<List<ResultItemEntity>>

    @Query("SELECT bestSeller FROM home_store_table")
    fun getBestSellers(): Flow<List<BestSellerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBestSeller(bestSeller: BestSellerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotSales(hotSales: HotSalesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllResults(resultItemEntity: List<ResultItemEntity>)

    @Transaction
    @Query("SELECT * FROM home_store_table WHERE id = :id_result")
    suspend fun getResultWithBestSeller(id_result: String): List<ResultWithBestSellers>

    @Transaction
    @Query("SELECT * FROM home_store_table WHERE id = :id_result")
    suspend fun getResultWithHotSales(id_result: String): List<ResultWithHotSales>
}