package com.example.ecommerce.data.repository

import com.example.ecommerce.data.api.model.ResultItemDto
import com.example.ecommerce.domain.model.ResultItem
import com.example.ecommerce.domain.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class RepositoryImpl @Inject constructor(
    private val remote: RemoteSource,
    private val local: LocalSource
) : Repository {

    /** Retrofit */
    override fun getHomeStore(): Flow<List<ResultItem>> =
        flow {
            val result = remote.getHomeStore()
            result.forEach { resultItemDto ->
                val bestSellerDto = resultItemDto.bestSeller
                bestSellerDto.forEach {
                    local.insertBestSeller(it.toBestSellerEntity())
                }
            }
            result.forEach { resultItemDto ->
                val hotSalesDto = resultItemDto.hotSales
                hotSalesDto.forEach {
                    local.insertHotSales(it.toHomeStoreEntity())
                }
            }
            local.insertAllResults(remote.getHomeStore().map { it.toResultItemEntity() })
//        emit(
//            offlineCacheHomeStore(remote.getHomeStore())
//        )
        }
//        local.readAllData().map { it.map { it.toResultItem() } }

    private suspend fun offlineCacheHomeStore(resultItems: List<ResultItemDto>): List<ResultItem> {
        return if (remote.hasInternetConnection()) {
            val resultEntity = resultItems.map { it.toResultItemEntity() }
            local.insertAllResults(resultEntity)
            remote.getHomeStore().map { it.toResultItem() }
        } else {
            var resultItem = emptyList<ResultItem>()
            local.readAllData().collect { resultEntities ->
                resultItem = resultEntities.map { it.toResultItem() }
            }
            return resultItem
        }
    }

    /** ROOM */
    suspend fun insertResult(resultItemDto: ResultItemDto) {
//        val resultItemEntity = local.mapDtoToEntity(remote.getHomeStore)
//        local.insertResult(resultItemEntity)
    }
}