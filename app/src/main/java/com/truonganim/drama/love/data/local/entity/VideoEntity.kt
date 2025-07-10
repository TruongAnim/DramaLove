package com.truonganim.drama.love.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.truonganim.drama.love.domain.model.Video
import com.truonganim.drama.love.domain.model.Subtitle

@Entity(tableName = "videos")
@TypeConverters(VideoConverters::class)
data class VideoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val duration: Long,
    val category: String,
    val tags: List<String>,
    val viewCount: Long,
    val likeCount: Long,
    val dislikeCount: Long,
    val shareCount: Long,
    val createdAt: Long,
    val updatedAt: Long,
    val subtitles: List<Subtitle>,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
    val isFavorite: Boolean = false,
    val addedToFavoritesAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "watch_history")
data class WatchHistoryEntity(
    @PrimaryKey val videoId: String,
    val watchedAt: Long = System.currentTimeMillis()
)

class VideoConverters {
    private val moshi = Moshi.Builder().build()
    
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val adapter = moshi.adapter<List<String>>(
            Types.newParameterizedType(List::class.java, String::class.java)
        )
        return adapter.toJson(value)
    }
    
    @TypeConverter
    fun toStringList(value: String): List<String> {
        val adapter = moshi.adapter<List<String>>(
            Types.newParameterizedType(List::class.java, String::class.java)
        )
        return adapter.fromJson(value) ?: emptyList()
    }
    
    @TypeConverter
    fun fromSubtitleList(value: List<Subtitle>): String {
        val adapter = moshi.adapter<List<Subtitle>>(
            Types.newParameterizedType(List::class.java, Subtitle::class.java)
        )
        return adapter.toJson(value)
    }
    
    @TypeConverter
    fun toSubtitleList(value: String): List<Subtitle> {
        val adapter = moshi.adapter<List<Subtitle>>(
            Types.newParameterizedType(List::class.java, Subtitle::class.java)
        )
        return adapter.fromJson(value) ?: emptyList()
    }
}

// Extension functions
fun VideoEntity.toDomain(): Video {
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
        subtitles = subtitles,
        isLiked = isLiked,
        isDisliked = isDisliked,
        isFavorite = isFavorite
    )
}

fun Video.toEntity(): VideoEntity {
    return VideoEntity(
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
        subtitles = subtitles,
        isLiked = isLiked,
        isDisliked = isDisliked,
        isFavorite = isFavorite
    )
}
