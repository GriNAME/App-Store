package com.example.ecommerce.domain.model


data class BestSeller(
    val id: Int,
    val title: String,
    val priceWithoutDiscount: Int,
    val discountPrice: Int,
    val isFavorites: Boolean,
    val picture: String
)