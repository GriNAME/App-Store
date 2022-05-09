package com.example.details.data.repository

import com.example.details.data.storage.model.mapToEntity
import com.example.details.data.storage.model.mapToModel
import com.example.details.domain.model.Details
import com.example.details.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val local: DetailsLocalSource,
    private val remote: DetailsRemoteSource
) : DetailsRepository {

    override fun getDetails(): Flow<Details> =
        if (remote.hasInternetConnection()) {
            flow {
                val entity = remote.getDetails().mapToEntity()
                local.insertDetails(entity)
                emit(entity.mapToModel())
            }
        } else {
            local.getDetails().let { flow ->
                flow.map {
                    it.mapToModel()
                }
            }
        }
}