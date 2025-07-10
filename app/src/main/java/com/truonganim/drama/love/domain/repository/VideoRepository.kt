package com.truonganim.drama.love.domain.repository

import com.truonganim.drama.love.domain.model.Video
import com.truonganim.drama.love.domain.model.VideoSection
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    
    // Remote operations
    suspend fun getHotVideos(page: Int, pageSize: Int): Result<List<Video>>
    suspend fun getNewVideos(page: Int, pageSize: Int): Result<List<Video>>
    suspend fun getFavoriteVideos(page: Int, pageSize: Int): Result<List<Video>>
    suspend fun getVideoById(videoId: String): Result<Video>
    suspend fun getVideosByCategory(categoryId: String, page: Int, pageSize: Int): Result<List<Video>>
    suspend fun searchVideos(query: String, page: Int, pageSize: Int): Result<List<Video>>
    suspend fun getVideoSections(): Result<List<VideoSection>>
    
    // User interactions
    suspend fun likeVideo(videoId: String): Result<Unit>
    suspend fun dislikeVideo(videoId: String): Result<Unit>
    suspend fun addToFavorites(videoId: String): Result<Unit>
    suspend fun removeFromFavorites(videoId: String): Result<Unit>
    suspend fun shareVideo(videoId: String): Result<Unit>
    suspend fun recordView(videoId: String): Result<Unit>
    
    // Local operations
    fun getLocalFavoriteVideos(): Flow<List<Video>>
    suspend fun saveFavoriteVideo(video: Video)
    suspend fun removeFavoriteVideo(videoId: String)
    
    // Watch history
    suspend fun addToWatchHistory(video: Video)
    fun getWatchHistory(): Flow<List<Video>>
    suspend fun clearWatchHistory()
}
