package com.example.cart.domain.repository

import com.example.cart.domain.model.CartInfo
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(): Flow<CartInfo>
}