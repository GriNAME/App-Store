package com.example.ecommerce.data.api.model


import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.domain.model.ResultItem
import com.google.gson.annotations.SerializedName

data class ResultItemDto(
    @SerializedName("best_seller")
    val bestSeller: List<BestSellerDto>,
    @SerializedName("home_store")
    val homeStore: List<HomeStoreDto>,
    @SerializedName("_id")
    val id: String
) {
    fun toResultItem() = ResultItem(
        bestSeller = bestSeller.map { it.toBestSeller() },
        homeStore = homeStore.map { it.toHomeStore() },
        id = id
    )

    fun toResultItemEntity() = ResultItemEntity(
        bestSeller = bestSeller.map { it.toBestSellerEntity() },
        homeStore = homeStore.map { it.toHomeStoreEntity() },
        id = id
    )
}