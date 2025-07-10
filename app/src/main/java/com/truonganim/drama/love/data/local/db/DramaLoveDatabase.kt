package com.truonganim.drama.love.data.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.truonganim.drama.love.data.local.entity.VideoEntity
import com.truonganim.drama.love.data.local.entity.WatchHistoryEntity
import com.truonganim.drama.love.data.local.entity.VideoConverters

@Database(
    entities = [
        VideoEntity::class,
        WatchHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(VideoConverters::class)
abstract class DramaLoveDatabase : RoomDatabase() {
    
    abstract fun videoDao(): VideoDao
    abstract fun watchHistoryDao(): WatchHistoryDao
}
