package com.example.ecommerce.data.repository

import com.example.ecommerce.data.api.HomeShopApi
import com.example.ecommerce.data.api.model.ResultItemDto
import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.domain.model.HomeStore
import com.example.ecommerce.domain.model.ResultItem
import com.example.ecommerce.domain.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class RepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
) : Repository {

    override fun getHomeStore(): Flow<List<ResultItem>> = remoteSource.getHomeStore()


//    // ROOM
//    fun getHomeShop(): Flow<List<ResultItemEntity>> {
//        return homeDao.readHomeShop()
//    }
//
//    suspend fun insertHopeShop(resultItems: List<ResultItemEntity>) {
//        homeDao.insertHomeData(resultItems)
//    }
}