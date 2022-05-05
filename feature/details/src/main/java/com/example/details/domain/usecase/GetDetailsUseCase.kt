package com.example.details.domain.usecase

import com.example.details.domain.model.Details
import com.example.details.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: DetailsRepository
) {

    fun execute(): Flow<Details> =
        repository.getDetails()
}