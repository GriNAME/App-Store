package com.example.ecommerce.data.api

import com.example.ecommerce.data.api.model.ResultItemDto
import com.example.ecommerce.data.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeShopApi {

    @Headers("x-apikey: $API_KEY")
    @GET("home")
    suspend fun getHomeStore(): Response<List<ResultItemDto>>
}