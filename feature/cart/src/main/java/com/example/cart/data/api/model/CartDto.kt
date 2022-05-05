package com.example.cart.data.api.model


import com.google.gson.annotations.SerializedName

data class CartDto(
    @SerializedName("basket")
    val basket: List<BasketDto>,
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("total")
    val total: Int
)