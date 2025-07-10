package com.truonganim.drama.love.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.truonganim.drama.love.domain.model.Video
import com.truonganim.drama.love.domain.model.Subtitle

@JsonClass(generateAdapter = true)
data class VideoDto(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail_url") val thumbnailUrl: String,
    @Json(name = "video_url") val videoUrl: String,
    @Json(name = "duration") val duration: Long,
    @Json(name = "category") val category: String,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "view_count") val viewCount: Long,
    @Json(name = "like_count") val likeCount: Long,
    @Json(name = "dislike_count") val dislikeCount: Long,
    @Json(name = "share_count") val shareCount: Long,
    @Json(name = "created_at") val createdAt: Long,
    @Json(name = "updated_at") val updatedAt: Long,
    @Json(name = "subtitles") val subtitles: List<SubtitleDto> = emptyList(),
    @Json(name = "is_liked") val isLiked: Boolean = false,
    @Json(name = "is_disliked") val isDisliked: Boolean = false,
    @Json(name = "is_favorite") val isFavorite: Boolean = false
)

@JsonClass(generateAdapter = true)
data class SubtitleDto(
    @Json(name = "id") val id: String,
    @Json(name = "language") val language: String,
    @Json(name = "language_code") val languageCode: String,
    @Json(name = "url") val url: String,
    @Json(name = "is_default") val isDefault: Boolean = false
)

// Extension functions to convert DTO to Domain model
fun VideoDto.toDomain(): Video {
    return Video(
        id = id,
        title = title,
        description = description,
        thumbnailUrl = thumbnailUrl,
        videoUrl = videoUrl,
        duration = duration,
        category = category,
        tags = tags,
        viewCount = viewCount,
        likeCount = likeCount,
        dislikeCount = dislikeCount,
        shareCount = shareCount,
        createdAt = createdAt,
        updatedAt = updatedAt,
        subtitles = subtitles.map { it.toDomain() },
        isLiked = isLiked,
        isDisliked = isDisliked,
        isFavorite = isFavorite
    )
}

fun SubtitleDto.toDomain(): Subtitle {
    return Subtitle(
        id = id,
        language = language,
        languageCode = languageCode,
        url = url,
        isDefault = isDefault
    )
}
