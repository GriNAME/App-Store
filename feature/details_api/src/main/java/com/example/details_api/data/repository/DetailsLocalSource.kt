package com.example.details_api.data.repository

import com.example.details_api.data.storage.CartDao
import com.example.details_api.data.storage.DetailsDao
import com.example.details_api.data.storage.model.*
import com.example.details_api.domain.model.CartItem
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

    fun getCartItems(): Flow<List<CartItemEntity>> =
        cartDao.getCartItems()

    suspend fun insertDetails(detailsEntity: DetailsEntity) =
        detailsDao.insertDetails(detailsEntity)

    suspend fun insertItemToCart(cartItem: CartItem) =
        cartDao.insertItemToCart(cartItem.mapToEntity())

    suspend fun updateItemToCart(cartItem: CartItem) =
        cartDao.updateItemToCart(cartItem.mapToEntity())

    suspend fun insertCartItem() {
        val product = ProductEntity(
            images = listOf("https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-13-pro-silver-select?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1631652954000"),
            price = 1800,
            title = "iPhone 13"
        )
        val cartItem = CartItem(
            0,
            product.mapToModel(),
            1
        )
        cartDao.insertItemToCart(cartItem.mapToEntity())
    }

    suspend fun deleteItemFromCart(cartItem: CartItem) =
        cartDao.deleteItemFromCart(cartItem.mapToEntity())
}