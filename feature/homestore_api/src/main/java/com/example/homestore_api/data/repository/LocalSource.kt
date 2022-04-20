package com.example.homestore_api.data.repository

import com.example.homestore_api.data.storage.StoreDao
import com.example.homestore_api.data.storage.entity.BestSellerEntity
import com.example.homestore_api.data.storage.entity.relation.ResultWithBestSellersAndHotSales
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalSource @Inject constructor(
    private val storeDao: StoreDao
) {

    fun getAllData(): Flow<ResultWithBestSellersAndHotSales> =
        storeDao.findAll()

    suspend fun insertAllResults(resultEntities: ResultWithBestSellersAndHotSales) =
        storeDao.insertAllResults(resultEntities)

    fun searchBestSellerByTitle(searchQuery: String): Flow<List<BestSellerEntity>> =
        storeDao.searchBestSellerByTitle(searchQuery)
}