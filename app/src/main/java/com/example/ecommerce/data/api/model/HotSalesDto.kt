package com.example.ecommerce.data.api.model


import com.example.ecommerce.data.storage.entity.HotSalesEntity
import com.example.ecommerce.domain.model.HomeStore
import com.google.gson.annotations.SerializedName

data class HotSalesDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_buy")
    val isBuy: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
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

    fun toHomeStoreEntity() = HotSalesEntity(
        id = id,
        isBuy = isBuy,
        isNew = isNew,
        picture = picture,
        subtitle = subtitle,
        title = title
    )
}