package com.example.cart.data.repository

import com.example.cart.data.storage.CartDao
import javax.inject.Inject

class CartLocalSource @Inject constructor(
    private val dao: CartDao
) {
}