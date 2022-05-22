package com.example.details_api.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.homestore_api.data.util.Constants.TABLE_CART_ITEM_NAME

@Entity(tableName = TABLE_CART_ITEM_NAME)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "product")
    val product: ProductEntity,
    @ColumnInfo(name = "quantity")
    val quantity: Int
)
