package com.truonganim.drama.love.domain.repository

import com.truonganim.drama.love.domain.model.User
import com.truonganim.drama.love.domain.model.CheckInReward
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    
    // Authentication
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(username: String, email: String, password: String): Result<User>
    suspend fun logout(): Result<Unit>
    suspend fun getCurrentUser(): Result<User?>
    
    // Profile
    suspend fun updateProfile(user: User): Result<User>
    suspend fun updateAvatar(avatarUrl: String): Result<Unit>
    
    // Check-in system
    suspend fun dailyCheckIn(): Result<CheckInReward>
    suspend fun getCheckInStatus(): Result<Boolean>
    
    // Points system
    suspend fun getUserPoints(): Result<Int>
    suspend fun addPoints(points: Int): Result<Unit>
    suspend fun deductPoints(points: Int): Result<Unit>
    
    // Local user data
    fun getLocalUser(): Flow<User?>
    suspend fun saveLocalUser(user: User)
    suspend fun clearLocalUser()
}
