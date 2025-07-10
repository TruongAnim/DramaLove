package com.truonganim.drama.love.presentation.screen.reward

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
fun RewardScreen(
    viewModel: RewardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Reward",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Điểm danh hàng ngày nhận thưởng",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        if (uiState.isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Đang tải thông tin...",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            // Points display
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Points",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${uiState.userPoints} điểm",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Điểm danh liên tiếp: ${uiState.consecutiveDays} ngày",
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Check-in button
            Button(
                onClick = { viewModel.performDailyCheckIn() },
                enabled = uiState.canCheckIn && !uiState.isCheckingIn,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (uiState.isCheckingIn) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = when {
                        uiState.isCheckingIn -> "Đang điểm danh..."
                        uiState.canCheckIn -> "Điểm danh hôm nay"
                        else -> "Đã điểm danh hôm nay"
                    }
                )
            }
            
            // Last reward display
            uiState.lastCheckInReward?.let { reward ->
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🎉 ${reward.message}",
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Nhận được: ${reward.points + reward.bonusPoints} điểm",
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
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
