package com.example.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.details.domain.model.Details
import com.example.details.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: GetDetailsUseCase
) : ViewModel() {

    val details: LiveData<Details> = useCase.execute().asLiveData()
}