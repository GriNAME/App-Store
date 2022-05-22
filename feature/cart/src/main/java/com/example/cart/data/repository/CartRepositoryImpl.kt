package com.example.cart.data.repository

import com.example.cart.data.storage.model.mapToModel
import com.example.cart.domain.model.CartInfo
import com.example.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val local: CartLocalSource
) : CartRepository {

    override fun getCart(): Flow<CartInfo> =
        local.getCart().let { flow ->
            flow.map {
                it.mapToModel()
            }
        }
}