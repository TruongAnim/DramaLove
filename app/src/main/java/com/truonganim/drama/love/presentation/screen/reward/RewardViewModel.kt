package com.truonganim.drama.love.presentation.screen.reward

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truonganim.drama.love.domain.model.CheckInReward
import com.truonganim.drama.love.domain.usecase.DailyCheckInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor(
    private val dailyCheckInUseCase: DailyCheckInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RewardUiState())
    val uiState: StateFlow<RewardUiState> = _uiState.asStateFlow()

    init {
        loadRewardData()
    }

    private fun loadRewardData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                // TODO: Load user points and check-in status
                // For now, just simulate loading
                kotlinx.coroutines.delay(1000)
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    userPoints = 150, // TODO: Get from repository
                    consecutiveDays = 5, // TODO: Get from repository
                    canCheckIn = true // TODO: Check from repository
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun performDailyCheckIn() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isCheckingIn = true)
            
            dailyCheckInUseCase()
                .onSuccess { reward ->
                    _uiState.value = _uiState.value.copy(
                        isCheckingIn = false,
                        canCheckIn = false,
                        lastCheckInReward = reward,
                        userPoints = _uiState.value.userPoints + reward.points + reward.bonusPoints,
                        consecutiveDays = reward.consecutiveDays
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isCheckingIn = false,
                        error = error.message
                    )
                }
        }
    }

    fun onRefresh() {
        loadRewardData()
    }
}

data class RewardUiState(
    val isLoading: Boolean = false,
    val isCheckingIn: Boolean = false,
    val userPoints: Int = 0,
    val consecutiveDays: Int = 0,
    val canCheckIn: Boolean = false,
    val lastCheckInReward: CheckInReward? = null,
    val error: String? = null
)
