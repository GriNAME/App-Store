package com.example.ecommerce.data.api.model


import com.google.gson.annotations.SerializedName

data class BestSellerDto(
    @SerializedName("discount_price")
    val discountPrice: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_favorites")
    val isFavorites: Boolean,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("price_without_discount")
    val priceWithoutDiscount: Int,
    @SerializedName("title")
    val title: String
)