package com.example.details_api.domain.repository

import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun getDetails(): Flow<Details>

    fun getCartList(): Flow<List<Product>>

    suspend fun insertDetails(details: Details)

    suspend fun insertProduct()

    suspend fun deleteItemFromCart(product: Product)
}