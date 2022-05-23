package com.example.cart.presentation.viewmodel

import androidx.lifecycle.*
import com.example.details_api.domain.model.CartItem
import com.example.details_api.domain.model.Product
import com.example.details_api.domain.usecase.CartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartItemUseCase: CartItemUseCase
) : ViewModel() {

    val cartItems: LiveData<List<CartItem>> = cartItemUseCase.getCartItems().asLiveData()
    val totalPrice: MutableLiveData<Int> = MutableLiveData()

    fun refreshTotalPrice() {
        val resultList = ArrayList<Int>()
        cartItems.let { liveData ->
            liveData.map { list ->
                list.map {
                    resultList.add(it.product.price * it.quantity)
                }
            }
        }

        totalPrice.value = resultList.sum()
    }

    fun updateItemToCart(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        cartItemUseCase.updateItemToCart(cartItem)
    }

    fun deleteItemFromCart(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        cartItemUseCase.deleteItemFromCart(cartItem)
    }
}