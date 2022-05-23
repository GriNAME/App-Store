package com.example.cart.presentation.viewmodel

import androidx.lifecycle.*
import com.example.details_api.domain.model.Details
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    val productList: LiveData<List<Product>> = getDetailsUseCase.getCartList().asLiveData()

    fun deleteItemFromCart(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        getDetailsUseCase.deleteItemFromCart(product)
    }
}