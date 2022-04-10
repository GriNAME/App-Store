package com.example.navigation

import androidx.navigation.NavController

class Navigator {

    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {

        NavigationFlow.HomeStoreFlow -> navController.navigate(MainNavGraphDirections.actionGlobalHomeStore())
        NavigationFlow.DetailsFlow -> navController.navigate(MainNavGraphDirections.actionGlobalDetails())
        NavigationFlow.CartFlow -> navController.navigate(MainNavGraphDirections.actionGlobalCart())
    }
}