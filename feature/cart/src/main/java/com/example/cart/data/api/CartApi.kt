package com.example.cart.data.api

import com.example.cart.data.api.model.CartDto
import com.example.homestore_api.data.util.Constants.CART_URL
import retrofit2.Response
import retrofit2.http.GET

interface CartApi {

    @GET(CART_URL)
    suspend fun getCart(): Response<CartDto>
}