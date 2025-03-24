package com.example.emotionalcompanionship.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emotionalcompanionship.ui.screens.*

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CreateCompanion : Screen("create_companion")
    object SelectCompanion : Screen("select_companion")
    object Memory : Screen("memory")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onStartClick = {
                    navController.navigate(Screen.CreateCompanion.route)
                }
            )
        }
        
        composable(Screen.CreateCompanion.route) {
            CreateCompanionScreen(
                onNextClick = {
                    navController.navigate(Screen.SelectCompanion.route)
                }
            )
        }
        
        composable(Screen.SelectCompanion.route) {
            SelectCompanionScreen(
                onCompanionSelected = {
                    navController.navigate(Screen.Memory.route)
                }
            )
        }
        
        composable(Screen.Memory.route) {
            MemoryScreen()
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
} 