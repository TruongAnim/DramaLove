package com.truonganim.drama.love.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.truonganim.drama.love.domain.model.User
import com.truonganim.drama.love.domain.model.CheckInReward

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id") val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "avatar_url") val avatarUrl: String?,
    @Json(name = "points") val points: Int = 0,
    @Json(name = "consecutive_checkin_days") val consecutiveCheckInDays: Int = 0,
    @Json(name = "last_checkin_date") val lastCheckInDate: Long? = null,
    @Json(name = "favorite_videos") val favoriteVideos: List<String> = emptyList(),
    @Json(name = "watch_history") val watchHistory: List<String> = emptyList(),
    @Json(name = "created_at") val createdAt: Long,
    @Json(name = "updated_at") val updatedAt: Long
)

@JsonClass(generateAdapter = true)
data class CheckInRewardDto(
    @Json(name = "points") val points: Int,
    @Json(name = "bonus_points") val bonusPoints: Int = 0,
    @Json(name = "consecutive_days") val consecutiveDays: Int,
    @Json(name = "message") val message: String
)

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name = "user") val user: UserDto,
    @Json(name = "token") val token: String
)

// Extension functions
fun UserDto.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email,
        avatarUrl = avatarUrl,
        points = points,
        consecutiveCheckInDays = consecutiveCheckInDays,
        lastCheckInDate = lastCheckInDate,
        favoriteVideos = favoriteVideos,
        watchHistory = watchHistory,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun CheckInRewardDto.toDomain(): CheckInReward {
    return CheckInReward(
        points = points,
        bonusPoints = bonusPoints,
        consecutiveDays = consecutiveDays,
        message = message
    )
}
