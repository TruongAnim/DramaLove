package com.truonganim.drama.love.presentation.screen.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Setting",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Cài đặt ứng dụng và tài khoản",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        if (uiState.isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Đang tải cài đặt...",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Dark Mode Setting
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.DarkMode,
                                contentDescription = "Dark Mode"
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Chế độ tối")
                        }
                        Switch(
                            checked = uiState.isDarkMode,
                            onCheckedChange = { viewModel.toggleDarkMode() }
                        )
                    }
                }
                
                // Notification Setting
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "Notifications"
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Thông báo")
                        }
                        Switch(
                            checked = uiState.isNotificationEnabled,
                            onCheckedChange = { viewModel.toggleNotification() }
                        )
                    }
                }
                
                // Auto Play Setting
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "Auto Play"
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Tự động phát")
                        }
                        Switch(
                            checked = uiState.isAutoPlayEnabled,
                            onCheckedChange = { viewModel.toggleAutoPlay() }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Logout Button
                Button(
                    onClick = { viewModel.logout() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(
                        Icons.Default.Logout,
                        contentDescription = "Logout"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Đăng xuất")
                }
            }
        }
        
        uiState.error?.let { error ->
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = "Lỗi: $error",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedButton(
            onClick = { viewModel.onRefresh() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Làm mới")
        }
    }
}
