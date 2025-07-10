package com.truonganim.drama.love.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = selectedTab == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Discovery", Icons.Default.Home),
    BottomNavItem("Shorts", Icons.Default.PlayArrow),
    BottomNavItem("Reward", Icons.Default.Star),
    BottomNavItem("Setting", Icons.Default.Settings)
)
