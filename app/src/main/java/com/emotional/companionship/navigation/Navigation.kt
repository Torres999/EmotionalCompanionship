package com.emotional.companionship.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emotional.companionship.ui.select.SelectDigitalHumanActivity
import com.emotional.companionship.ui.profile.ProfileFragment
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "select") {
        composable("select") {
            SelectDigitalHumanActivity()
        }
        composable("profile") {
            ProfileFragment()
        }
        composable("create") {
            CreateDigitalHumanActivity()
        }
    }
} 