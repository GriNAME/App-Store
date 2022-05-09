package com.example.cart.data.repository

import com.example.cart.data.storage.model.mapToEntity
import com.example.cart.data.storage.model.mapToModel
import com.example.cart.domain.model.Cart
import com.example.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val local: CartLocalSource,
    private val remote: CartRemoteSource
) : CartRepository{

    override fun getCart(): Flow<Cart> =
        if (remote.hasInternetConnection()) {
            flow {
                val entity = remote.getCart().mapToEntity()
                local.insertCart(entity)
                emit(entity.mapToModel())
            }
        } else {
            local.getCart().let { flow ->
                flow.map {
                    it.mapToModel()
                }
            }

        }
}