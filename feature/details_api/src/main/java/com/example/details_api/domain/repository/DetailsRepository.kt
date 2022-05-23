package com.example.details_api.domain.repository

import com.example.details_api.domain.model.CartItem
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun getDetails(): Flow<Details>

    fun getCartItems(): Flow<List<CartItem>>

    suspend fun insertItemToCart(cartItem: CartItem)

    suspend fun updateItemToCart(cartItem: CartItem)

    suspend fun insertProduct()

    suspend fun deleteItemFromCart(cartItem: CartItem)
}