package com.example.ecommerce.data.storage.entity

import androidx.room.ColumnInfo
import com.example.ecommerce.domain.model.BestSeller

data class BestSellerEntity(
    @ColumnInfo(name = "discount_price")
    val discountPrice: Int,
    @ColumnInfo(name = "best_seller_id")
    val id: Int,
    @ColumnInfo(name = "is_favorites")
    val isFavorites: Boolean,
    @ColumnInfo(name = "best_seller_picture")
    val picture: String,
    @ColumnInfo(name = "price_without_discount")
    val priceWithoutDiscount: Int,
    @ColumnInfo(name = "best_seller_title")
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
}
