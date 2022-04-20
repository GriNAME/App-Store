package com.example.homestore_api.data.api.model


import com.google.gson.annotations.SerializedName

data class ResultItemDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("best_seller")
    val bestSeller: List<BestSellerDto>,
    @SerializedName("home_store")
    val hotSales: List<HotSalesDto>
)