package com.example.details_api.data.storage

import androidx.room.*
import com.example.details_api.data.storage.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM product")
    fun getCartList(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCart(productEntity: ProductEntity)

    @Delete
    suspend fun deleteItemFromCart(productEntity: ProductEntity)
}