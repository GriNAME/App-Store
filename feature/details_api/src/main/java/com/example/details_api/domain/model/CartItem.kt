package com.example.details_api.domain.model

data class CartItem(
    val id: Long,
    val product: Product,
    val quantity: Int
)
