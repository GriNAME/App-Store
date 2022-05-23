package com.example.details_api.data.repository

import com.example.details_api.data.storage.model.mapToEntity
import com.example.details_api.data.storage.model.mapToModel
import com.example.details_api.data.storage.model.mapToModels
import com.example.details_api.domain.model.CartItem
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import com.example.details_api.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val local: DetailsLocalSource,
    private val remote: DetailsRemoteSource
) : DetailsRepository {

    override fun getDetails(): Flow<Details> =
        if (remote.hasInternetConnection()) {
            flow {
                val entity = remote.getDetails().mapToEntity()
                local.insertDetails(entity)
                emit(entity.mapToModel())
            }
        } else {
            local.getDetails().let { flow ->
                flow.map {
                    it.mapToModel()
                }
            }
        }

    override fun getCartItems(): Flow<List<CartItem>> =
        local.getCartItems().let { flow ->
            flow.map { list ->
                list.mapToModels()
            }
        }

    override suspend fun insertItemToCart(cartItem: CartItem) {
        val newCartItem = CartItem(
            0,
            cartItem.product,
            1
        )
        local.insertItemToCart(newCartItem)
    }

    override suspend fun updateItemToCart(cartItem: CartItem) {
        local.updateItemToCart(cartItem)
    }

    override suspend fun insertProduct() {
        local.insertCartItem()
    }

    override suspend fun deleteItemFromCart(cartItem: CartItem) {
        local.deleteItemFromCart(cartItem)
    }
}