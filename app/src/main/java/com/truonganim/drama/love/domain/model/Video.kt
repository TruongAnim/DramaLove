package com.truonganim.drama.love.domain.model

data class Video(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String, // m3u8 link
    val duration: Long, // in seconds
    val category: String,
    val tags: List<String>,
    val viewCount: Long,
    val likeCount: Long,
    val dislikeCount: Long,
    val shareCount: Long,
    val createdAt: Long,
    val updatedAt: Long,
    val subtitles: List<Subtitle> = emptyList(),
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
    val isFavorite: Boolean = false
)

data class Subtitle(
    val id: String,
    val language: String,
    val languageCode: String,
    val url: String, // .srt file URL
    val isDefault: Boolean = false
)
