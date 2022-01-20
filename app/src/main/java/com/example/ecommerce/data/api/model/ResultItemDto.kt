package com.example.ecommerce.data.api.model


import com.google.gson.annotations.SerializedName

data class ResultItemDto(
    @SerializedName("best_seller")
    val bestSeller: List<BestSellerDto>,
    @SerializedName("home_store")
    val homeStore: List<HomeStoreDto>,
    @SerializedName("_id")
    val id: String
)