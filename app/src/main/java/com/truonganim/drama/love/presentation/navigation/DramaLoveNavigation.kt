package com.truonganim.drama.love.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun DramaLoveNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            // SplashScreen()
        }
        
        composable(Screen.Home.route) {
            // HomeScreen(navController)
        }
        
        composable(Screen.Shorts.route) {
            // ShortsScreen(navController)
        }
        
        composable(Screen.CheckIn.route) {
            // CheckInScreen(navController)
        }
        
        composable(Screen.Profile.route) {
            // ProfileScreen(navController)
        }
        
        composable(
            route = Screen.Player.route,
            arguments = Screen.Player.arguments
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId") ?: ""
            // PlayerScreen(videoId, navController)
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Shorts : Screen("shorts")
    object CheckIn : Screen("checkin")
    object Profile : Screen("profile")
    object Player : Screen("player/{videoId}") {
        fun createRoute(videoId: String) = "player/$videoId"
        val arguments = listOf(
            androidx.navigation.navArgument("videoId") {
                type = androidx.navigation.NavType.StringType
            }
        )
    }
}
