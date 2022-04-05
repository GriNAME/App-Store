package com.example.ecommerce.data.repository

import com.example.ecommerce.data.storage.StoreDao
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.HotSalesEntity
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.data.storage.entity.relation.ResultWithBestSellersAndHotSales
import com.example.ecommerce.domain.model.ResultItem
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