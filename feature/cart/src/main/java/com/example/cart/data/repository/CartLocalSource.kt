package com.example.cart.data.repository

import com.example.cart.data.storage.CartDao
import com.example.cart.data.storage.model.CartEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartLocalSource @Inject constructor(
    private val dao: CartDao
) {

    fun getCart(): Flow<CartEntity> = dao.getCart()

    suspend fun insertCart(cartEntity: CartEntity) =
        dao.insertCart(cartEntity)
}