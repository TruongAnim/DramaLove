package com.truonganim.drama.love.data.local.db

import androidx.room.*
import com.truonganim.drama.love.data.local.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    
    @Query("SELECT * FROM videos WHERE isFavorite = 1 ORDER BY addedToFavoritesAt DESC")
    fun getFavoriteVideos(): Flow<List<VideoEntity>>
    
    @Query("SELECT * FROM videos WHERE id = :videoId")
    suspend fun getVideoById(videoId: String): VideoEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: VideoEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideoEntity>)
    
    @Update
    suspend fun updateVideo(video: VideoEntity)
    
    @Query("DELETE FROM videos WHERE id = :videoId")
    suspend fun deleteVideo(videoId: String)
    
    @Query("UPDATE videos SET isFavorite = 1, addedToFavoritesAt = :timestamp WHERE id = :videoId")
    suspend fun addToFavorites(videoId: String, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE videos SET isFavorite = 0 WHERE id = :videoId")
    suspend fun removeFromFavorites(videoId: String)
    
    @Query("DELETE FROM videos WHERE isFavorite = 0")
    suspend fun clearNonFavoriteVideos()
}
