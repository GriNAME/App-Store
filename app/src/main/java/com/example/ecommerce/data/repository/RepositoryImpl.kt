package com.example.ecommerce.data.repository

import com.example.ecommerce.data.storage.entity.relation.mapToEntity
import com.example.ecommerce.data.storage.entity.relation.mapToModels
import com.example.ecommerce.domain.model.ResultItem
import com.example.ecommerce.domain.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class RepositoryImpl @Inject constructor(
    private val remote: RemoteSource,
    private val local: LocalSource
) : Repository {

    /** Retrofit */
    override fun getHomeStore(): Flow<List<ResultItem>> = flow {
        if (remote.hasInternetConnection()) {

            val entities = remote.getHomeStore().map { dto ->
                dto.mapToEntity()
            }
            local.insertAllResults(entities)
            emit(entities.mapToModels())
        } else {
            local.readAllData()
        }

    }

    /** ROOM */

}