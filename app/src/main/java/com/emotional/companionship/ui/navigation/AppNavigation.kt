package com.emotional.companionship.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emotional.companionship.ui.home.HomeScreen
import com.emotional.companionship.ui.memory.MemoryScreen
import com.emotional.companionship.ui.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Memory.route) {
            MemoryScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
} 