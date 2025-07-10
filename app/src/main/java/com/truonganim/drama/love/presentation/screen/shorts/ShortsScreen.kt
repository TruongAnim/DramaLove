package com.truonganim.drama.love.presentation.screen.shorts

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
fun ShortsScreen(
    viewModel: ShortsViewModel = hiltViewModel()
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
            text = "Shorts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Video ngắn giống TikTok",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        if (uiState.isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Đang tải video...",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "📱 Short Video Features",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Action buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IconButton(
                            onClick = { viewModel.onVideoLike("") },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Like",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        
                        IconButton(
                            onClick = { viewModel.onVideoShare("") },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            Icon(
                                Icons.Default.Share,
                                contentDescription = "Share",
                                tint = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                        
                        IconButton(
                            onClick = { viewModel.onVideoComment("") },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Icon(
                                Icons.Default.Comment,
                                contentDescription = "Comment",
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        }
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
        
        Button(
            onClick = { viewModel.onRefresh() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Làm mới")
        }
    }
}
