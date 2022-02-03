package com.example.ecommerce.domain.model


data class ResultItem(
    val id: String,
    val bestSeller: List<BestSeller>,
    val homeStore: List<HomeStore>
)