package com.example.homestore_api.data.repository

import com.example.homestore_api.data.storage.entity.mapToModels
import com.example.homestore_api.data.storage.entity.relation.mapToEntity
import com.example.homestore_api.data.storage.entity.relation.mapToModel
import com.example.homestore_api.domain.model.BestSeller
import com.example.homestore_api.domain.model.ResultItem
import com.example.homestore_api.domain.repository.Repository
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

    override fun getHomeStore(): Flow<ResultItem> =
        if (remote.hasInternetConnection()) {
            flow {
                val entity = remote.getHomeStore().mapToEntity()

                if (entity != null) {
                    local.insertAllResults(entity)
                }
                emit(entity.mapToModel())
            }
        } else {
            local.getAllData().let { flow ->
                flow.map { list -> list.mapToModel() }
            }
        }

    override fun searchBestSellerByTitle(searchQuery: String): Flow<List<BestSeller>> =
        local.searchBestSellerByTitle(searchQuery).map { it.mapToModels() }
}