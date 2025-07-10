package com.truonganim.drama.love.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    // Add repositories here when needed
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        initializeApp()
    }

    private fun initializeApp() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, progress = 0f)

            try {
                // Simulate loading with progress updates
                for (i in 1..20) {
                    delay(100) // 100ms * 20 = 2000ms total
                    val progress = i / 20f
                    _uiState.value = _uiState.value.copy(progress = progress)
                }

                // TODO: Load initial data from API
                // - Check user authentication
                // - Load app configuration
                // - Preload essential data

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isInitialized = true,
                    progress = 1f
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

data class SplashUiState(
    val isLoading: Boolean = false,
    val isInitialized: Boolean = false,
    val progress: Float = 0f,
    val error: String? = null
)
