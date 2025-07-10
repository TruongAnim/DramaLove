package com.truonganim.drama.love.utils

object Constants {
    
    // API
    const val BASE_URL = "https://api.dramalove.com/"
    const val API_TIMEOUT = 30L
    
    // Database
    const val DATABASE_NAME = "drama_love_database"
    const val DATABASE_VERSION = 1
    
    // Preferences
    const val PREF_NAME = "drama_love_prefs"
    const val PREF_USER_TOKEN = "user_token"
    const val PREF_USER_ID = "user_id"
    const val PREF_FIRST_LAUNCH = "first_launch"
    
    // Video Player
    const val VIDEO_CACHE_SIZE = 100 * 1024 * 1024L // 100MB
    const val VIDEO_BUFFER_DURATION = 30000 // 30 seconds
    
    // Pagination
    const val PAGE_SIZE = 20
    const val INITIAL_PAGE = 1
    
    // Check-in
    const val DAILY_CHECKIN_POINTS = 10
    const val CONSECUTIVE_CHECKIN_BONUS = 5
    
    // Video Categories
    const val CATEGORY_HOT = "hot"
    const val CATEGORY_NEW = "new"
    const val CATEGORY_FAVORITE = "favorite"
    
    // Notification
    const val NOTIFICATION_CHANNEL_ID = "drama_love_notifications"
    const val NOTIFICATION_CHANNEL_NAME = "DramaLove Notifications"
}
