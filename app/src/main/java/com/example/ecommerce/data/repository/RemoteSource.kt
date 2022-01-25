package com.example.ecommerce.data.repository

import com.example.ecommerce.data.api.HomeShopApi
import com.example.ecommerce.data.api.model.ResultItemDto
import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.domain.model.HomeStore
import com.example.ecommerce.domain.model.ResultItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val api: HomeShopApi
) {

    fun getHomeStore(): Flow<List<ResultItem>> = flow {

        val resultItemsDto = api.getHomeStore().body()!!
        emit(mapToResultItem(resultItemsDto))
    }

    private fun mapToResultItem(resultItemDto: List<ResultItemDto>): List<ResultItem> {

        val list = ArrayList<ResultItem>()
        val bestSellers = ArrayList<BestSeller>()
        val hotSales = ArrayList<HomeStore>()

        for (result in resultItemDto) {

            for (bestSeller in result.bestSeller) {

                val bestSellerResult = BestSeller(
                    bestSeller.discountPrice,
                    bestSeller.id,
                    bestSeller.isFavorites,
                    bestSeller.picture,
                    bestSeller.priceWithoutDiscount,
                    bestSeller.title
                )
                bestSellers.add(bestSellerResult)
            }

            for (homeStore in result.homeStore) {

                val homeStoreResult = HomeStore(
                    homeStore.id,
                    homeStore.isBuy,
                    homeStore.isNew,
                    homeStore.picture,
                    homeStore.subtitle,
                    homeStore.title
                )
                hotSales.add(homeStoreResult)
            }

            val resultItem = ResultItem(bestSellers, hotSales, result.id)
            list.add(resultItem)
        }
        return list.toList()
    }

}