package com.example.homestore_api.domain.model


data class BestSeller(
    val id: Int,
    val title: String,
    val priceWithoutDiscount: Int,
    val discountPrice: Int,
    val isFavorites: Boolean,
    val picture: String
)