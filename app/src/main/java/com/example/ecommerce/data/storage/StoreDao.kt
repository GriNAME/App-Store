package com.example.ecommerce.data.storage

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.HotSalesEntity
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.data.storage.entity.relation.ResultWithBestSellersAndHotSales
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface StoreDao {

    @Query("SELECT * FROM home_store_table")
    fun readAllData(): Flow<List<ResultWithBestSellersAndHotSales>>

    @Transaction
    @Query("SELECT * FROM home_store_table")
    fun findAll(): Flow<List<ResultWithBestSellersAndHotSales>>

    fun findAllDistinct() = findAll().distinctUntilChanged() // notify after change data

    @Transaction
    suspend fun insertAllResults(entities: List<ResultWithBestSellersAndHotSales>) {
        for (entity in entities) {
            insertResultItem(entity.resultItemEntity)
            deleteBestSellersByParentId(entity.resultItemEntity.id) // Delete all data
            insertBestSeller(entity.bestSellerEntities)
            deleteHotSalesByParentId(entity.resultItemEntity.id) // Delete all data
            insertHotSales(entity.hotSalesEntities)
        }
    }

    @Insert(onConflict = REPLACE)
    suspend fun insertResultItem(entity: ResultItemEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertBestSeller(bestSeller: List<BestSellerEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertHotSales(hotSales: List<HotSalesEntity>)

    @Query("DELETE FROM best_seller_table WHERE parent_id = :parentId")
    suspend fun deleteBestSellersByParentId(parentId: String)

    @Query("DELETE FROM hot_sales_table WHERE parent_id = :parentId")
    suspend fun deleteHotSalesByParentId(parentId: String)

    @Query("SELECT * FROM best_seller_table WHERE title LIKE :searchTitle")
    fun searchBestSellerByTitle(searchTitle: String): Flow<List<BestSellerEntity>>
}