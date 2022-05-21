package com.example.cart.presentation.viewmodel

import androidx.lifecycle.*
import com.example.cart.domain.model.CartInfo
import com.example.cart.domain.usecase.GetCartUseCase
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    val cart: LiveData<CartInfo> = getCartUseCase.execute().asLiveData()
    val productList: LiveData<List<Product>> = getDetailsUseCase.getCartList().asLiveData()
    val totalPrice: LiveData<List<Int>> = MutableLiveData()

    fun addToTotalPrice(price: Int) {
        
    }

    fun deleteFromTotalPrice(price: Int) {

    }

    fun deleteItemFromCart(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        getDetailsUseCase.deleteItemFromCart(product)
    }
}