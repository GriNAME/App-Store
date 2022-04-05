package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.domain.model.ResultItem
import com.example.ecommerce.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(): Flow<ResultItem> =
        repository.getHomeStore()

    fun searchBestSellerByName(searchQuery: String): Flow<List<BestSeller>> =
        repository.searchBestSellerByTitle(searchQuery)
}