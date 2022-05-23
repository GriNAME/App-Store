package com.example.details_api.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.details_api.domain.model.CartItem
import com.example.homestore_api.data.util.Constants.TABLE_CART_ITEM

@Entity(tableName = TABLE_CART_ITEM)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_item")
    val id_item: Long = 0,
    @Embedded
    val product: ProductEntity,
    @ColumnInfo(name = "quantity")
    val quantity: Int
)

fun CartItem.mapToEntity() = CartItemEntity(
    id,
    product.mapToEntity(),
    quantity
)

fun CartItemEntity.mapToModel() = CartItem(
    id_item,
    product.mapToModel(),
    quantity
)

fun List<CartItemEntity>.mapToModels() = map {
    it.mapToModel()
}
