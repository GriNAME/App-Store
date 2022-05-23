package com.example.details_api.data.storage

import androidx.room.*
import com.example.details_api.data.storage.model.CartItemEntity
import com.example.details_api.data.storage.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_item")
    fun getCartItems(): Flow<List<CartItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemToCart(cartItemEntity: CartItemEntity)

    @Update
    suspend fun updateItemToCart(cartItemEntity: CartItemEntity)

    @Delete
    suspend fun deleteItemFromCart(cartItemEntity: CartItemEntity)
}