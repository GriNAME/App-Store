package com.example.ecommerce.data.api.model


import com.google.gson.annotations.SerializedName

data class HomeStoreDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_buy")
    val isBuy: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
)