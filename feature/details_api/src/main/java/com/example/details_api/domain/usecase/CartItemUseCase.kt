package com.example.details_api.domain.usecase

import com.example.details_api.domain.model.CartItem
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CartItemUseCase @Inject constructor(
    private val repository: DetailsRepository
) {

    fun getCartItems(): Flow<List<CartItem>> =
        repository.getCartItems()

    suspend fun insertItemToCart(cartItem: CartItem) =
        repository.insertItemToCart(cartItem)

    suspend fun updateItemToCart(cartItem: CartItem) =
        repository.updateItemToCart(cartItem)

    suspend fun insertProduct() =
        repository.insertProduct()

    suspend fun deleteItemFromCart(cartItem: CartItem) =
        repository.deleteItemFromCart(cartItem)
}