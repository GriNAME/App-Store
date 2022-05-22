package com.example.details_api.data.repository

import com.example.details_api.data.storage.CartDao
import com.example.details_api.data.storage.DetailsDao
import com.example.details_api.data.storage.model.ProductEntity
import com.example.details_api.data.storage.model.DetailsEntity
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsLocalSource @Inject constructor(
    private val detailsDao: DetailsDao,
    private val cartDao: CartDao
) {

    fun getDetails(): Flow<DetailsEntity> =
        detailsDao.getDetails()

    fun getCartList(): Flow<List<ProductEntity>> =
        cartDao.getCartList()

    suspend fun insertDetails(detailsEntity: DetailsEntity) =
        detailsDao.insertDetails(detailsEntity)

    suspend fun insertCart(details: Details) {
        val cart = ProductEntity(
            0,
            details.images,
            details.price,
            details.title
        )
        cartDao.insertCart(cart)
    }

    suspend fun insertProduct() {
        val cart = ProductEntity(
            0,
            listOf("https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-13-pro-silver-select?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1631652954000"),
            1800,
            "iPhone 13"
        )
        cartDao.insertCart(cart)
    }

    suspend fun deleteItemFromCart(product: Product) {
        val productEntity = ProductEntity(
            product.id,
            product.images,
            product.price,
            product.title
        )
        cartDao.deleteItemFromCart(productEntity)
    }
}