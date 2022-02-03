package com.example.ecommerce.data.repository

import com.example.ecommerce.data.storage.StoreDao
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.HotSalesEntity
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.domain.model.ResultItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalSource @Inject constructor(
    private val storeDao: StoreDao
) {

    fun readAllData(): Flow<List<ResultItemEntity>> =
        storeDao.readAllData()

    suspend fun insertAllResults(resultEntities: List<ResultItemEntity>) =
        storeDao.insertAllResults(resultEntities)

    suspend fun insertBestSeller(bestSeller: BestSellerEntity) =
        storeDao.insertBestSeller(bestSeller)

    suspend fun insertHotSales(hotSales: HotSalesEntity) =
        storeDao.insertHotSales(hotSales)

    fun mapToResultItem(resultItemDto: List<ResultItemEntity>): List<ResultItemEntity> {

        val list = ArrayList<ResultItemEntity>()

        for (result in resultItemDto) {
            list.add(result)
        }
        return list.toList()
    }

    fun mapToResultItemFromFlow(resultItemDto: List<ResultItemEntity>): List<ResultItemEntity> {

        val list = ArrayList<ResultItemEntity>()

        for (result in resultItemDto) {
            list.add(result)
        }
        return list.toList()
    }
}