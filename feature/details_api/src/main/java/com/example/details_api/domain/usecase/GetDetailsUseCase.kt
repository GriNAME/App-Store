package com.example.details_api.domain.usecase

import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import com.example.details_api.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: DetailsRepository
) {

    fun execute(): Flow<Details> =
        repository.getDetails()

    fun getCartList(): Flow<List<Product>> =
        repository.getCartList()

    suspend fun insertDetails(details: Details) =
        repository.insertDetails(details)

    suspend fun deleteItemFromCart(product: Product) =
        repository.deleteItemFromCart(product)
}