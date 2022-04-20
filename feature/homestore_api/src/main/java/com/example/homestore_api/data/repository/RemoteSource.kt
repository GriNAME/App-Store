package com.example.homestore_api.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.homestore_api.data.api.HomeShopApi
import com.example.homestore_api.data.api.model.ResultItemDto
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val api: HomeShopApi,
    private val context: Context
) {

    suspend fun getHomeStore(): ResultItemDto {

        return api.getHomeStore().let {
            it.body()!!
        }
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