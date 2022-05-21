package com.example.details_api.data.repository

import com.example.details_api.data.storage.DetailsDao
import com.example.details_api.data.storage.model.ProductEntity
import com.example.details_api.data.storage.model.DetailsEntity
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsLocalSource @Inject constructor(
    private val dao: DetailsDao
) {

    fun getDetails(): Flow<DetailsEntity> =
        dao.getDetails()

    fun getCartList(): Flow<List<ProductEntity>> =
        dao.getCartList()

    suspend fun insertDetails(detailsEntity: DetailsEntity) =
        dao.insertDetails(detailsEntity)

    suspend fun insertCart(details: Details) {
        val cart = ProductEntity(
            0,
            details.images,
            details.price,
            details.title
        )
        dao.insertCart(cart)
    }

    suspend fun deleteItemFromCart(product: Product) {
        val productEntity = ProductEntity(
            product.id,
            product.images,
            product.price,
            product.title
        )
        dao.deleteItemFromCart(productEntity)
    }
}