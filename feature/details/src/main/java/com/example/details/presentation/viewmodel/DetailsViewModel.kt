package com.example.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.details_api.domain.model.CartItem
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.model.Details
import com.example.details_api.domain.usecase.CartItemUseCase
import com.example.details_api.domain.usecase.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: GetDetailsUseCase,
    private val cartItemUseCase: CartItemUseCase
) : ViewModel() {

    val details: LiveData<Details> = detailsUseCase.execute().asLiveData()
    val cartItems: LiveData<List<CartItem>> = cartItemUseCase.getCartItems().asLiveData()

    fun insertItemToCart(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        cartItemUseCase.insertItemToCart(cartItem)
    }
}