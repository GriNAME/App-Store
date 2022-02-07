package com.example.ecommerce.data.repository

import com.example.ecommerce.data.storage.entity.mapToModels
import com.example.ecommerce.data.storage.entity.relation.mapToEntity
import com.example.ecommerce.data.storage.entity.relation.mapToModels
import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.domain.model.ResultItem
import com.example.ecommerce.domain.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class RepositoryImpl @Inject constructor(
    private val remote: RemoteSource,
    private val local: LocalSource
) : Repository {

    override fun getHomeStore(): Flow<List<ResultItem>> =
        if (remote.hasInternetConnection()) {
            flow {
                val entities = remote.getHomeStore().map { dto ->
                    dto.mapToEntity()
                }

                if (entities != null) {
                    local.insertAllResults(entities)
                }
                emit(entities.mapToModels())
            }
        } else {
            local.getAllData().let { flow ->
                flow.map { list -> list.mapToModels() }
            }
        }

    override fun searchBestSellerByTitle(searchQuery: String): Flow<List<BestSeller>> =
        local.searchBestSellerByTitle(searchQuery).map { it.mapToModels() }
}