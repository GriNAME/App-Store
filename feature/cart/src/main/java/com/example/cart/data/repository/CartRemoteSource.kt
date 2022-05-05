package com.example.cart.data.repository

import com.example.cart.data.api.CartApi
import javax.inject.Inject

class CartRemoteSource @Inject constructor(
    private val api: CartApi
) {
}