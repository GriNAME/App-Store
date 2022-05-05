package com.example.cart.data.api.model


import com.google.gson.annotations.SerializedName

data class BasketDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
)