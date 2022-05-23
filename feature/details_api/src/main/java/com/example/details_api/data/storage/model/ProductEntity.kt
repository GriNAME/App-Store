package com.example.details_api.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.details_api.domain.model.Product
import com.example.homestore_api.data.util.Constants.TABLE_PRODUCT

@Entity(tableName = TABLE_PRODUCT)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "images")
    val images: List<String>,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "title")
    val title: String
)

fun ProductEntity.mapToModel() = Product(
    id,
    images,
    price,
    title
)

fun Product.mapToEntity() = ProductEntity(
    id,
    images,
    price,
    title
)

fun List<ProductEntity>.mapToModels() = map {
    it.mapToModel()
}
