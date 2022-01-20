package com.example.ecommerce.domain.repository

import com.example.ecommerce.domain.model.ResultItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getHomeStore(): Flow<List<ResultItem>>
}