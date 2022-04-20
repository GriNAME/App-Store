package com.example.homestore.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.homestore_api.domain.model.BestSeller
import com.example.homestore_api.domain.model.ResultItem
import com.example.homestore_api.domain.usecase.GetHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase
) : ViewModel() {

    val home: LiveData<ResultItem> = getHomeUseCase.execute().asLiveData()

    var searchedBestSeller: LiveData<List<BestSeller>> = MediatorLiveData()

    fun searchBestSellerByName(searchQuery: String): LiveData<List<BestSeller>> {
        return getHomeUseCase.searchBestSellerByName(searchQuery).asLiveData()
    }
}