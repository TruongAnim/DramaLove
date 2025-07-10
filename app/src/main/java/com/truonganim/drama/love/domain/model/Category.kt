package com.truonganim.drama.love.domain.model

data class Category(
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val videoCount: Int,
    val isPopular: Boolean = false
)

data class VideoSection(
    val title: String,
    val videos: List<Video>,
    val sectionType: SectionType
)

enum class SectionType {
    HOT,
    NEW,
    FAVORITE,
    RECOMMENDED,
    TRENDING
}
