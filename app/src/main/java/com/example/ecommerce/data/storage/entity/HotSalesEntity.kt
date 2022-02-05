package com.example.ecommerce.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.ecommerce.data.api.model.HotSalesDto
import com.example.ecommerce.data.util.Constants.TABLE_HOT_SALES_NAME
import com.example.ecommerce.domain.model.HotSales

@Entity(
    tableName = TABLE_HOT_SALES_NAME,
    primaryKeys = ["parent_id", "id"]
)
data class HotSalesEntity(
    @ColumnInfo(name = "parent_id")
    val parentId: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subtitle")
    val subtitle: String,
    @ColumnInfo(name = "is_buy")
    val isBuy: Boolean,
    @ColumnInfo(name = "is_new")
    val isNew: Boolean,
    @ColumnInfo(name = "picture")
    val picture: String
)

fun HotSalesDto.mapToEntity(parentId: String) = HotSalesEntity(
    parentId = parentId,
    id = id,
    title = title,
    subtitle = subtitle,
    isBuy = isBuy,
    isNew = isNew,
    picture = picture
)

fun List<HotSalesDto>.mapToEntities(parentId: String) = map { dto ->
    dto.mapToEntity(parentId)
}

fun HotSalesEntity.mapToModel() = HotSales(
    id = id,
    title = title,
    subtitle = subtitle,
    isBuy = isBuy,
    isNew = isNew,
    picture = picture
)

fun List<HotSalesEntity>.mapToModels() = map { entity ->
    entity.mapToModel()
}