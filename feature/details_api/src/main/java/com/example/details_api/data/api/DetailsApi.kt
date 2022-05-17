package com.example.details_api.data.api

import com.example.homestore_api.data.util.Constants.DETAILS_URL
import retrofit2.Response
import retrofit2.http.GET

interface DetailsApi {

    @GET(DETAILS_URL)
    suspend fun getDetails(): Response<com.example.details_api.data.api.model.DetailsDto>
}