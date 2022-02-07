package com.example.ecommerce.presentation.viewmodel

import androidx.lifecycle.*
import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.domain.usecase.GetHomeUseCase
import com.example.ecommerce.domain.model.ResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase
) : ViewModel() {

    val home: LiveData<List<ResultItem>> = getHomeUseCase.execute().asLiveData()

    var searchedBestSeller: LiveData<List<BestSeller>> = MediatorLiveData()

    fun searchBestSellerByName(searchQuery: String): LiveData<List<BestSeller>> {
        return getHomeUseCase.searchBestSellerByName(searchQuery).asLiveData()
    }
}