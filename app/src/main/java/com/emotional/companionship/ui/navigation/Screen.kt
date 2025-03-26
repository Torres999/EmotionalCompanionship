package com.emotional.companionship.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("navigation_home")
    object Memory : Screen("navigation_history")
    object Profile : Screen("navigation_profile")
} 