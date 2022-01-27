package com.example.ecommerce.data.api.model


import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.domain.model.BestSeller
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
) {
    fun toBestSeller() = BestSeller(
        discountPrice = discountPrice,
        id = id,
        isFavorites = isFavorites,
        picture = picture,
        priceWithoutDiscount = priceWithoutDiscount,
        title = title
    )

    fun toBestSellerEntity() = BestSellerEntity(
        discountPrice = discountPrice,
        id = id,
        isFavorites = isFavorites,
        picture = picture,
        priceWithoutDiscount = priceWithoutDiscount,
        title = title
    )
}