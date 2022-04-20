package com.example.homestore_api.domain.model


data class ResultItem(
    val id: String,
    val bestSeller: List<BestSeller>,
    val hotSales: List<HotSales>
)