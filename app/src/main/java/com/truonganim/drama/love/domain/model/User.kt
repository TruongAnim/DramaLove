package com.truonganim.drama.love.domain.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val avatarUrl: String?,
    val points: Int = 0,
    val consecutiveCheckInDays: Int = 0,
    val lastCheckInDate: Long? = null,
    val favoriteVideos: List<String> = emptyList(),
    val watchHistory: List<String> = emptyList(),
    val createdAt: Long,
    val updatedAt: Long
)

data class CheckInReward(
    val points: Int,
    val bonusPoints: Int = 0,
    val consecutiveDays: Int,
    val message: String
)
