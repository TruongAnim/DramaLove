package com.truonganim.drama.love.presentation.screen.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truonganim.drama.love.domain.model.VideoSection
import com.truonganim.drama.love.domain.usecase.GetVideoSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoveryViewModel @Inject constructor(
    private val getVideoSectionsUseCase: GetVideoSectionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DiscoveryUiState())
    val uiState: StateFlow<DiscoveryUiState> = _uiState.asStateFlow()

    init {
        loadVideoSections()
    }

    fun loadVideoSections() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            getVideoSectionsUseCase()
                .onSuccess { sections ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        videoSections = sections
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }

    fun onVideoClick(videoId: String) {
        // Handle video click - navigate to player
        // This will be handled by the UI layer
    }

    fun onRefresh() {
        loadVideoSections()
    }
}

data class DiscoveryUiState(
    val isLoading: Boolean = false,
    val videoSections: List<VideoSection> = emptyList(),
    val error: String? = null
)
