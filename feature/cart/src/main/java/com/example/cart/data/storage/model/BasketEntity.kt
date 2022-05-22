package com.example.cart.data.storage.model

import androidx.room.ColumnInfo
import com.example.cart.domain.model.Basket


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

fun BasketEntity.mapToModel() = Basket(
    id,
    images,
    price,
    title
)

fun List<BasketEntity>.mapToModels() = map {
    it.mapToModel()
}