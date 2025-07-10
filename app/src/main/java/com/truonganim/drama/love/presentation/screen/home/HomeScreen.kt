package com.truonganim.drama.love.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.truonganim.drama.love.presentation.components.BottomNavigationBar
import com.truonganim.drama.love.presentation.screen.discovery.DiscoveryScreen
import com.truonganim.drama.love.presentation.screen.shorts.ShortsScreen
import com.truonganim.drama.love.presentation.screen.reward.RewardScreen
import com.truonganim.drama.love.presentation.screen.setting.SettingScreen

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> DiscoveryScreen(
                    onVideoClick = { videoId ->
                        // Navigate to video player
                        // navController.navigate("player/$videoId")
                    }
                )
                1 -> ShortsScreen()
                2 -> RewardScreen()
                3 -> SettingScreen()
            }
        }
    }
}
