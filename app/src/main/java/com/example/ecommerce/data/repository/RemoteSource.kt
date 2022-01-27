package com.example.ecommerce.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.ecommerce.R
import com.example.ecommerce.data.api.HomeShopApi
import com.example.ecommerce.data.api.model.ResultItemDto
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.data.util.NetworkResult
import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.domain.model.HomeStore
import com.example.ecommerce.domain.model.ResultItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val api: HomeShopApi,
    private val context: Context
) {

    suspend fun getHomeStore(): List<ResultItemDto> {

        return api.getHomeStore().let {
            it.body()!!
        }
    }

    fun mapToResultItem(resultItemDto: List<ResultItemDto>): List<ResultItem> {

        val list = ArrayList<ResultItem>()

        for (result in resultItemDto) {
            list.add(result.toResultItem())
        }
        return list.toList()
    }

//    suspend fun getStoreSafeCall(apiKey: String) {
//        getHomeStore = NetworkResult.Loading()
//
//        if (hasInternetConnection()) {
//            try {
//                val response = api.getHomeStore().body()!!
//                foodJokeResponse.value = handleFoodJokeResponse(response)
//
//                val foodJoke = foodJokeResponse.value!!.data
//
//                if (foodJoke != null)
//                    offlineCacheFoodJoke(foodJoke)
//
//            } catch (e: Exception) {
//                foodJokeResponse.value = NetworkResult.Error(context.getString(R.string.recipe_not_found))
//            }
//        } else {
//            foodJokeResponse.value = NetworkResult.Error(context.getString(R.string.no_internet_connection))
//        }
//    }


    fun hasInternetConnection(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activityNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activityNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}