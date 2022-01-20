package com.example.ecommerce.domain.model


data class ResultItem(
    val bestSeller: List<BestSeller>,
    val homeStore: List<HomeStore>,
    val id: String
)