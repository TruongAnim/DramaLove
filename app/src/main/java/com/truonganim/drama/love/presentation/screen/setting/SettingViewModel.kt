package com.truonganim.drama.love.presentation.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truonganim.drama.love.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    // Add repositories here when needed
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                // TODO: Load user profile from repository
                // For now, just simulate loading
                kotlinx.coroutines.delay(1000)
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    // TODO: Replace with actual user data
                    isDarkMode = false,
                    isNotificationEnabled = true,
                    isAutoPlayEnabled = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun toggleDarkMode() {
        _uiState.value = _uiState.value.copy(
            isDarkMode = !_uiState.value.isDarkMode
        )
        // TODO: Save preference to repository
    }

    fun toggleNotification() {
        _uiState.value = _uiState.value.copy(
            isNotificationEnabled = !_uiState.value.isNotificationEnabled
        )
        // TODO: Save preference to repository
    }

    fun toggleAutoPlay() {
        _uiState.value = _uiState.value.copy(
            isAutoPlayEnabled = !_uiState.value.isAutoPlayEnabled
        )
        // TODO: Save preference to repository
    }

    fun logout() {
        viewModelScope.launch {
            try {
                // TODO: Implement logout logic
                // Clear user data, navigate to login
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }

    fun onRefresh() {
        loadUserProfile()
    }
}

data class SettingUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val isDarkMode: Boolean = false,
    val isNotificationEnabled: Boolean = true,
    val isAutoPlayEnabled: Boolean = true,
    val error: String? = null
)
