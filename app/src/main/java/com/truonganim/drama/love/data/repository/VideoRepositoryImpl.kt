package com.truonganim.drama.love.data.repository

import com.truonganim.drama.love.data.local.db.VideoDao
import com.truonganim.drama.love.data.local.db.WatchHistoryDao
import com.truonganim.drama.love.data.local.entity.WatchHistoryEntity
import com.truonganim.drama.love.data.local.entity.toDomain
import com.truonganim.drama.love.data.local.entity.toEntity
import com.truonganim.drama.love.data.remote.api.VideoApiService
import com.truonganim.drama.love.data.remote.dto.toDomain
import com.truonganim.drama.love.domain.model.Video
import com.truonganim.drama.love.domain.model.VideoSection
import com.truonganim.drama.love.domain.model.SectionType
import com.truonganim.drama.love.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepositoryImpl @Inject constructor(
    private val apiService: VideoApiService,
    private val videoDao: VideoDao,
    private val watchHistoryDao: WatchHistoryDao
) : VideoRepository {

    override suspend fun getHotVideos(page: Int, pageSize: Int): Result<List<Video>> {
        return try {
            val response = apiService.getHotVideos(page, pageSize)
            if (response.isSuccessful) {
                val videos = response.body()?.map { it.toDomain() } ?: emptyList()
                Result.success(videos)
            } else {
                Result.failure(Exception("Failed to fetch hot videos: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getNewVideos(page: Int, pageSize: Int): Result<List<Video>> {
        return try {
            val response = apiService.getNewVideos(page, pageSize)
            if (response.isSuccessful) {
                val videos = response.body()?.map { it.toDomain() } ?: emptyList()
                Result.success(videos)
            } else {
                Result.failure(Exception("Failed to fetch new videos: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFavoriteVideos(page: Int, pageSize: Int): Result<List<Video>> {
        return try {
            val response = apiService.getFavoriteVideos(page, pageSize)
            if (response.isSuccessful) {
                val videos = response.body()?.map { it.toDomain() } ?: emptyList()
                Result.success(videos)
            } else {
                Result.failure(Exception("Failed to fetch favorite videos: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVideoById(videoId: String): Result<Video> {
        return try {
            val response = apiService.getVideoById(videoId)
            if (response.isSuccessful) {
                val video = response.body()?.toDomain()
                if (video != null) {
                    Result.success(video)
                } else {
                    Result.failure(Exception("Video not found"))
                }
            } else {
                Result.failure(Exception("Failed to fetch video: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVideosByCategory(categoryId: String, page: Int, pageSize: Int): Result<List<Video>> {
        return try {
            val response = apiService.getVideosByCategory(categoryId, page, pageSize)
            if (response.isSuccessful) {
                val videos = response.body()?.map { it.toDomain() } ?: emptyList()
                Result.success(videos)
            } else {
                Result.failure(Exception("Failed to fetch videos by category: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchVideos(query: String, page: Int, pageSize: Int): Result<List<Video>> {
        return try {
            val response = apiService.searchVideos(query, page, pageSize)
            if (response.isSuccessful) {
                val videos = response.body()?.map { it.toDomain() } ?: emptyList()
                Result.success(videos)
            } else {
                Result.failure(Exception("Failed to search videos: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVideoSections(): Result<List<VideoSection>> {
        return try {
            // Fetch different sections concurrently
            val hotVideosResult = getHotVideos(1, 10)
            val newVideosResult = getNewVideos(1, 10)
            val favoriteVideosResult = getFavoriteVideos(1, 10)

            val sections = mutableListOf<VideoSection>()
            
            hotVideosResult.getOrNull()?.let { videos ->
                if (videos.isNotEmpty()) {
                    sections.add(VideoSection("Hot Videos", videos, SectionType.HOT))
                }
            }
            
            newVideosResult.getOrNull()?.let { videos ->
                if (videos.isNotEmpty()) {
                    sections.add(VideoSection("New Videos", videos, SectionType.NEW))
                }
            }
            
            favoriteVideosResult.getOrNull()?.let { videos ->
                if (videos.isNotEmpty()) {
                    sections.add(VideoSection("Popular Videos", videos, SectionType.FAVORITE))
                }
            }

            Result.success(sections)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun likeVideo(videoId: String): Result<Unit> {
        return try {
            val response = apiService.likeVideo(videoId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to like video: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun dislikeVideo(videoId: String): Result<Unit> {
        return try {
            val response = apiService.dislikeVideo(videoId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to dislike video: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToFavorites(videoId: String): Result<Unit> {
        return try {
            val response = apiService.addToFavorites(videoId)
            if (response.isSuccessful) {
                videoDao.addToFavorites(videoId)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to add to favorites: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeFromFavorites(videoId: String): Result<Unit> {
        return try {
            val response = apiService.removeFromFavorites(videoId)
            if (response.isSuccessful) {
                videoDao.removeFromFavorites(videoId)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to remove from favorites: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun shareVideo(videoId: String): Result<Unit> {
        return try {
            val response = apiService.shareVideo(videoId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to share video: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun recordView(videoId: String): Result<Unit> {
        return try {
            val response = apiService.recordView(videoId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to record view: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getLocalFavoriteVideos(): Flow<List<Video>> {
        return videoDao.getFavoriteVideos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveFavoriteVideo(video: Video) {
        videoDao.insertVideo(video.toEntity().copy(isFavorite = true))
    }

    override suspend fun removeFavoriteVideo(videoId: String) {
        videoDao.removeFromFavorites(videoId)
    }

    override suspend fun addToWatchHistory(video: Video) {
        // Save video to local database
        videoDao.insertVideo(video.toEntity())
        // Add to watch history
        watchHistoryDao.addToWatchHistory(WatchHistoryEntity(video.id))
        
        // Keep only last 100 entries
        val count = watchHistoryDao.getWatchHistoryCount()
        if (count > 100) {
            watchHistoryDao.removeOldestEntries(count - 100)
        }
    }

    override fun getWatchHistory(): Flow<List<Video>> {
        return watchHistoryDao.getWatchHistory().map { historyEntities ->
            historyEntities.mapNotNull { historyEntity ->
                videoDao.getVideoById(historyEntity.videoId)?.toDomain()
            }
        }
    }

    override suspend fun clearWatchHistory() {
        watchHistoryDao.clearWatchHistory()
    }
}
