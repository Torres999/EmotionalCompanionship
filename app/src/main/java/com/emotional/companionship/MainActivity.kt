package com.emotional.companionship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emotional.companionship.ui.navigation.AppNavigation
import com.emotional.companionship.ui.navigation.Screen
import com.emotional.companionship.ui.theme.EmotionalCompanionshipTheme
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 设置状态栏颜色为顶部标题栏背景色
        window.statusBarColor = ContextCompat.getColor(this, R.color.top_bar_color)
        
        // 告诉系统UI，内容不应该覆盖系统栏
        WindowCompat.setDecorFitsSystemWindows(window, true)
        
        // 设置状态栏文字/图标为白色
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false
        
        setContent {
            EmotionalCompanionshipTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    // 根据当前导航位置获取对应的标题
    val currentTitle = when {
        currentDestination?.hierarchy?.any { it.route == Screen.Home.route } == true -> "首页"
        currentDestination?.hierarchy?.any { it.route == Screen.Memory.route } == true -> "记忆库"
        currentDestination?.hierarchy?.any { it.route == Screen.Profile.route } == true -> "我的"
        else -> "首页"
    }
    
    val topBarColor = Color(ContextCompat.getColor(LocalContext.current, R.color.top_bar_color).toInt()) // 使用colors.xml中的颜色
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        text = currentTitle,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) 
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = topBarColor,
                    titleContentColor = Color.White
                ),
                modifier = Modifier.height(48.dp)
            )
        },
        bottomBar = {
            NavigationBar {
                listOf(
                    Triple(Screen.Home, "首页", Icons.Default.Home),
                    Triple(Screen.Memory, "记忆库", Icons.Default.Bookmark),
                    Triple(Screen.Profile, "我的", Icons.Default.Person)
                ).forEach { (screen, label, icon) ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                // 避免创建重复的页面栈
                                popUpTo(Screen.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(ContextCompat.getColor(LocalContext.current, R.color.bottom_nav_selected).toInt()),
                            selectedTextColor = Color(ContextCompat.getColor(LocalContext.current, R.color.bottom_nav_selected).toInt()),
                            unselectedIconColor = Color(ContextCompat.getColor(LocalContext.current, R.color.bottom_nav_unselected).toInt()),
                            unselectedTextColor = Color(ContextCompat.getColor(LocalContext.current, R.color.bottom_nav_unselected).toInt())
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AppNavigation(navController)
        }
    }
} 