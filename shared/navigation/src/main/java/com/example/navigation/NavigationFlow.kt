package com.example.navigation

sealed class NavigationFlow {

    object HomeStoreFlow : NavigationFlow()
    object DetailsFlow : NavigationFlow()
    object CartFlow : NavigationFlow()
}