package com.example.homestore_api.domain.repository

import com.example.homestore_api.domain.model.BestSeller
import com.example.homestore_api.domain.model.ResultItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getHomeStore(): Flow<ResultItem>

    fun searchBestSellerByTitle(searchQuery: String): Flow<List<BestSeller>>
}