package com.example.cart.data.storage.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cart.data.api.model.CartDto
import com.example.cart.domain.model.Cart
import com.example.homestore_api.data.util.Constants.TABLE_CART_NAME

@Entity(tableName = TABLE_CART_NAME)
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "basket")
    val basket: List<BasketEntity>,
    @ColumnInfo(name = "delivery")
    val delivery: String,
    @ColumnInfo(name = "total")
    val total: Int
)

fun CartDto.mapToEntity() = CartEntity(
    id,
    basket.mapToEntities(),
    delivery,
    total
)

fun CartEntity.mapToModel() = Cart(
    basket.mapToModels(),
    delivery,
    id,
    total
)