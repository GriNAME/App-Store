package com.example.homestore_api.domain.model


data class HotSales(
    val id: Int,
    val title: String,
    val subtitle: String,
    val isBuy: Boolean,
    val isNew: Boolean,
    val picture: String
)