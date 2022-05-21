package com.example.details_api.data.repository

import com.example.details_api.data.storage.model.mapToEntity
import com.example.details_api.data.storage.model.mapToModel
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

    override fun getCartList(): Flow<List<Product>> =
        local.getCartList().let { flow ->
            flow.map { list ->
                list.map {
                    it.mapToModel()
                }
            }
        }


    override suspend fun insertDetails(details: Details) {
        local.insertCart(details)
    }

    override suspend fun deleteItemFromCart(product: Product) {
        local.deleteItemFromCart(product)
    }
}