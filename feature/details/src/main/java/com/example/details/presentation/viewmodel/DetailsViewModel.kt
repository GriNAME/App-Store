package com.example.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import com.example.details_api.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: GetDetailsUseCase
) : ViewModel() {

    val details: LiveData<Details> = useCase.execute().asLiveData()
    val productList: LiveData<List<Product>> = useCase.getCartList().asLiveData()

    fun insertDetails(details: Details) = viewModelScope.launch(Dispatchers.IO) {
        useCase.insertDetails(details)
    }
}