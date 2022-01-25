package com.example.ecommerce.data.storage.entity

data class BestSellerEntity(
    val discountPrice: Int,
    val id: Int,
    val isFavorites: Boolean,
    val picture: String,
    val priceWithoutDiscount: Int,
    val title: String
)
