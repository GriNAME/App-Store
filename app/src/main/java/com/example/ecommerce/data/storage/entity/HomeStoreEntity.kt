package com.example.ecommerce.data.storage.entity

data class HomeStoreEntity(
    val id: Int,
    val isBuy: Boolean,
    val isNew: Boolean,
    val picture: String,
    val subtitle: String,
    val title: String
)
