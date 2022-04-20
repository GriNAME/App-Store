package com.example.homestore_api.data.api.model


import com.google.gson.annotations.SerializedName

data class HotSalesDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("is_buy")
    val isBuy: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("picture")
    val picture: String
)