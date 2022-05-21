package com.example.details_api.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_cart_item")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "product")
    val product: ProductEntity,
    @ColumnInfo(name = "quantity")
    val quantity: Int
)
