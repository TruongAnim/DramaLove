package com.truonganim.drama.love.di

import android.content.Context
import androidx.room.Room
import com.truonganim.drama.love.data.local.db.DramaLoveDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDramaLoveDatabase(
        @ApplicationContext context: Context
    ): DramaLoveDatabase {
        return Room.databaseBuilder(
            context,
            DramaLoveDatabase::class.java,
            "drama_love_database"
        ).build()
    }

    @Provides
    fun provideVideoDao(database: DramaLoveDatabase): com.truonganim.drama.love.data.local.db.VideoDao {
        return database.videoDao()
    }

    @Provides
    fun provideWatchHistoryDao(database: DramaLoveDatabase): com.truonganim.drama.love.data.local.db.WatchHistoryDao {
        return database.watchHistoryDao()
    }
}
