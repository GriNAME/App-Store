package com.example.details.data.repository

import com.example.details.data.storage.DetailsDao
import com.example.details.data.storage.model.DetailsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsLocalSource @Inject constructor(
    private val dao: DetailsDao
) {

    fun getDetails(): Flow<DetailsEntity> =
        dao.getDetails()

    suspend fun insertDetails(detailsEntity: DetailsEntity) =
        dao.insertDetails(detailsEntity)
}