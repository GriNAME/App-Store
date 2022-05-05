package com.example.details.domain.repository

import com.example.details.domain.model.Details
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun getDetails(): Flow<Details>
}