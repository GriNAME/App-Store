package com.example.homestore_api.data.api

import com.example.homestore_api.data.api.model.ResultItemDto
import retrofit2.Response
import retrofit2.http.GET

interface HomeShopApi {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomeStore(): Response<ResultItemDto>
}