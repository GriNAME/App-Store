package com.example.homestore_api.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.homestore_api.data.api.model.BestSellerDto
import com.example.homestore_api.data.util.Constants.TABLE_BEST_SELLER_NAME
import com.example.homestore_api.domain.model.BestSeller

@Entity(
    tableName = TABLE_BEST_SELLER_NAME,
    primaryKeys = ["parent_id", "id"]
)
data class BestSellerEntity(
    @ColumnInfo(name = "parent_id")
    val parentId: String,
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
    val picture: String
)

fun BestSellerDto.mapToEntity(parentId: String) = BestSellerEntity(
    parentId = parentId,
    id = id,
    title = title,
    priceWithoutDiscount = priceWithoutDiscount,
    discountPrice = discountPrice,
    isFavorites = isFavorites,
    picture = picture
)

fun List<BestSellerDto>.mapToEntities(parentId: String) = map {
    it.mapToEntity(parentId)
}

fun BestSellerEntity.mapToModel() = BestSeller(
    id = id,
    title = title,
    priceWithoutDiscount = priceWithoutDiscount,
    discountPrice = discountPrice,
    isFavorites = isFavorites,
    picture = picture
)

fun List<BestSellerEntity>.mapToModels() = map { entity ->
    entity.mapToModel()
}