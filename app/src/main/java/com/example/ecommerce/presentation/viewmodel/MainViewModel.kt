package com.example.ecommerce.presentation.viewmodel

import androidx.lifecycle.*
import com.example.ecommerce.domain.usecase.GetHomeUseCase
import com.example.ecommerce.domain.model.ResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getHomeUseCase: GetHomeUseCase
) : ViewModel() {

    val home: LiveData<List<ResultItem>> = getHomeUseCase.execute().asLiveData()
}