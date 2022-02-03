package com.example.ecommerce.data.api.model


import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.domain.model.ResultItem
import com.google.gson.annotations.SerializedName

data class ResultItemDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("best_seller")
    val bestSeller: List<BestSellerDto>,
    @SerializedName("home_store")
    val hotSales: List<HotSalesDto>
) {
    fun toResultItem() = ResultItem(
        id = id,
        bestSeller = bestSeller.map { it.toBestSeller() },
        homeStore = hotSales.map { it.toHomeStore() }
    )

    fun toResultItemEntity() = ResultItemEntity(
        id = id,
        bestSeller = bestSeller.map { it.toBestSellerEntity() },
        hotSales = hotSales.map { it.toHomeStoreEntity() }
    )
}