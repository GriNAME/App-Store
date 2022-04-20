package com.example.navigation

import androidx.navigation.NavController

class Navigator {

    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {

        NavigationFlow.HomeStoreFlow -> navController.navigate(MainGraphDirections.actionGlobalHomeStoreFlow())
        NavigationFlow.DetailsFlow -> navController.navigate(MainGraphDirections.actionGlobalToDetailsFlow())
        NavigationFlow.CartFlow -> navController.navigate(MainGraphDirections.actionGlobalToCartFlow())
    }
}