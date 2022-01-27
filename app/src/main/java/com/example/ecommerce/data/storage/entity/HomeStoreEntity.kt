package com.example.ecommerce.data.storage.entity

import androidx.room.ColumnInfo
import com.example.ecommerce.domain.model.HomeStore

data class HomeStoreEntity(
    @ColumnInfo(name = "home_store_id")
    val id: Int,
    @ColumnInfo(name = "is_buy")
    val isBuy: Boolean,
    @ColumnInfo(name = "is_new")
    val isNew: Boolean,
    @ColumnInfo(name = "home_store_picture")
    val picture: String,
    @ColumnInfo(name = "subtitle")
    val subtitle: String,
    @ColumnInfo(name = "home_store_title")
    val title: String
) {
    fun toHomeStore() = HomeStore(
        id = id,
        isBuy = isBuy,
        isNew = isNew,
        picture = picture,
        subtitle = subtitle,
        title = title
    )
}
