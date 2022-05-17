package com.example.details_api.domain.repository

import com.example.details_api.domain.model.Details
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun getDetails(): Flow<Details>
}