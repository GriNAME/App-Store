package com.example.ecommerce.domain.model


data class HomeStore(
    val id: Int,
    val title: String,
    val subtitle: String,
    val isBuy: Boolean,
    val isNew: Boolean,
    val picture: String
)