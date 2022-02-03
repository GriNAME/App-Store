package com.example.ecommerce.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.data.util.Constants.TABLE_BEST_SELLER_NAME
import com.example.ecommerce.domain.model.BestSeller

@Entity(tableName = TABLE_BEST_SELLER_NAME)
data class BestSellerEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "price_without_discount")
    val priceWithoutDiscount: Int,
    @ColumnInfo(name = "discount_price")
    val discountPrice: Int,
    @ColumnInfo(name = "is_favorites")
    val isFavorites: Boolean,
    @ColumnInfo(name = "picture")
    val picture: String,
    @ColumnInfo(name = "id_result")
    val idResult: String = ""
) {
    fun toBestSeller() = BestSeller(
        id = id,
        title = title,
        priceWithoutDiscount = priceWithoutDiscount,
        discountPrice = discountPrice,
        isFavorites = isFavorites,
        picture = picture
    )
}
