package com.truonganim.drama.love.presentation.screen.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truonganim.drama.love.domain.model.Category
import com.truonganim.drama.love.domain.model.Video
import com.truonganim.drama.love.domain.model.Subtitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoveryViewModel @Inject constructor(
    // Add repositories here when needed
) : ViewModel() {

    private val _uiState = MutableStateFlow(DiscoveryUiState())
    val uiState: StateFlow<DiscoveryUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isCategoriesLoading = true, error = null)

            try {
                // Fake loading delay
                delay(500)

                // Fake categories data
                val categories = listOf(
                    Category("1", "Hành Động", "Phim hành động kịch tính", "https://picsum.photos/200/300?random=1", 25, true),
                    Category("2", "Tình Cảm", "Phim tình cảm lãng mạn", "https://picsum.photos/200/300?random=2", 18, true),
                    Category("3", "Hài Hước", "Phim hài vui nhộn", "https://picsum.photos/200/300?random=3", 12, false),
                    Category("4", "Kinh Dị", "Phim kinh dị đáng sợ", "https://picsum.photos/200/300?random=4", 8, false),
                    Category("5", "Khoa Học", "Phim khoa học viễn tưởng", "https://picsum.photos/200/300?random=5", 15, false),
                    Category("6", "Cổ Trang", "Phim cổ trang Trung Quốc", "https://picsum.photos/200/300?random=6", 22, true)
                )

                _uiState.value = _uiState.value.copy(
                    isCategoriesLoading = false,
                    categories = categories,
                    selectedCategory = categories.first()
                )

                // Load movies for first category
                loadMoviesForCategory(categories.first().id)

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isCategoriesLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun onCategorySelected(category: Category) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
        loadMoviesForCategory(category.id)
    }

    private fun loadMoviesForCategory(categoryId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isMoviesLoading = true, error = null)

            try {
                // Fake loading delay
                delay(1000)

                // Generate fake movies for category
                val movies = generateFakeMovies(categoryId)

                _uiState.value = _uiState.value.copy(
                    isMoviesLoading = false,
                    movies = movies
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isMoviesLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun generateFakeMovies(categoryId: String): List<Video> {
        val categoryName = _uiState.value.categories.find { it.id == categoryId }?.name ?: "Unknown"

        return (1..12).map { index ->
            Video(
                id = "${categoryId}_$index",
                title = "$categoryName Movie $index",
                description = "Mô tả chi tiết cho bộ phim $categoryName số $index. Đây là một bộ phim hay và hấp dẫn.",
                thumbnailUrl = "https://picsum.photos/300/400?random=${categoryId}${index}",
                videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_1mb.mp4",
                duration = (90..180).random() * 60L, // 90-180 minutes in seconds
                category = categoryName,
                tags = listOf("Tag1", "Tag2", "Tag3"),
                viewCount = (1000..100000).random().toLong(),
                likeCount = (100..10000).random().toLong(),
                dislikeCount = (10..1000).random().toLong(),
                shareCount = (50..5000).random().toLong(),
                createdAt = System.currentTimeMillis() - (1..30).random() * 24 * 60 * 60 * 1000L,
                updatedAt = System.currentTimeMillis(),
                subtitles = listOf(
                    Subtitle("1", "Tiếng Việt", "vi", "https://example.com/subtitle_vi.srt", true),
                    Subtitle("2", "English", "en", "https://example.com/subtitle_en.srt", false)
                )
            )
        }
    }

    fun onVideoClick(videoId: String) {
        // Handle video click - navigate to player
        // This will be handled by the UI layer
    }

    fun onSearchClick() {
        // Handle search click
        // This will be handled by the UI layer
    }

    fun onRefresh() {
        loadCategories()
    }
}

data class DiscoveryUiState(
    val isCategoriesLoading: Boolean = false,
    val isMoviesLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val movies: List<Video> = emptyList(),
    val error: String? = null
)
