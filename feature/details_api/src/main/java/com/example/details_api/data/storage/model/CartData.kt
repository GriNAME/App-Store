package com.example.details_api.data.storage.model

import androidx.room.Embedded

data class CartEntity(
    @Embedded
    val cartItemEntities: List<CartItemEntity>,
    val totalPrice: Int
)
