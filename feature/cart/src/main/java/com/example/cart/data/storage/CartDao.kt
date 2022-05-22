package com.example.cart.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.cart.data.storage.model.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCart(): Flow<CartEntity>

    @Insert(onConflict = IGNORE)
    suspend fun insertCart(cartEntity: CartEntity)
}