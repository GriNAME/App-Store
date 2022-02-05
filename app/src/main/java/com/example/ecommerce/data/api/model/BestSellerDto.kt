package com.example.ecommerce.data.api.model


import com.google.gson.annotations.SerializedName

data class BestSellerDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("discount_price")
    val discountPrice: Int,
    @SerializedName("price_without_discount")
    val priceWithoutDiscount: Int,
    @SerializedName("is_favorites")
    val isFavorites: Boolean,
    @SerializedName("picture")
    val picture: String
)