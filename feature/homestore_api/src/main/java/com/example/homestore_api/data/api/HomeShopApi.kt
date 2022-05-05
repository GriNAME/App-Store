package com.example.homestore_api.data.api

import com.example.homestore_api.data.api.model.ResultItemDto
import com.example.homestore_api.data.util.Constants.HOME_STORE_URL
import retrofit2.Response
import retrofit2.http.GET

interface HomeShopApi {

    @GET(HOME_STORE_URL)
    suspend fun getHomeStore(): Response<ResultItemDto>
}