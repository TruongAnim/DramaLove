package com.truonganim.drama.love.data.local.db

import androidx.room.*
import com.truonganim.drama.love.data.local.entity.WatchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchHistoryDao {
    
    @Query("SELECT * FROM watch_history ORDER BY watchedAt DESC")
    fun getWatchHistory(): Flow<List<WatchHistoryEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchHistory(watchHistory: WatchHistoryEntity)
    
    @Query("DELETE FROM watch_history WHERE videoId = :videoId")
    suspend fun removeFromWatchHistory(videoId: String)
    
    @Query("DELETE FROM watch_history")
    suspend fun clearWatchHistory()
    
    @Query("SELECT COUNT(*) FROM watch_history")
    suspend fun getWatchHistoryCount(): Int
    
    @Query("DELETE FROM watch_history WHERE videoId IN (SELECT videoId FROM watch_history ORDER BY watchedAt ASC LIMIT :count)")
    suspend fun removeOldestEntries(count: Int)
}
