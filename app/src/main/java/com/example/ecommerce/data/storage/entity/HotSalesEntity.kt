package com.example.ecommerce.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.data.util.Constants.TABLE_HOT_SALES_NAME
import com.example.ecommerce.domain.model.HomeStore

@Entity(tableName = TABLE_HOT_SALES_NAME)
data class HotSalesEntity(
    @PrimaryKey(autoGenerate = false)
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
    val picture: String,
    @ColumnInfo(name = "id_result")
    val idResult: String = ""
) {
    fun toHomeStore() = HomeStore(
        id = id,
        title = title,
        subtitle = subtitle,
        isBuy = isBuy,
        isNew = isNew,
        picture = picture
    )
}
