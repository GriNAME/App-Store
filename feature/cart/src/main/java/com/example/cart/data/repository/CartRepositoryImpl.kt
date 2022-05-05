package com.example.cart.data.repository

import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val local: CartLocalSource,
    private val remote: CartRemoteSource
) {


}