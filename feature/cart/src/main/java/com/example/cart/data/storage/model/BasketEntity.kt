package com.example.cart.data.storage.model


import androidx.room.ColumnInfo
import com.example.cart.data.api.model.BasketDto

data class BasketEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "images")
    val images: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "title")
    val title: String
)

fun BasketDto.mapToEntity() = BasketEntity(
    id,
    images,
    price,
    title
)

fun List<BasketDto>.mapToEntities() = map {
    it.mapToEntity()
}