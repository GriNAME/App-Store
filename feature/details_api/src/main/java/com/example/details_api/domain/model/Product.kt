package com.example.details_api.domain.model

data class Product(
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String
)
