package com.truonganim.drama.love.data.repository

import com.truonganim.drama.love.data.remote.api.UserApiService
import com.truonganim.drama.love.data.remote.dto.LoginRequest
import com.truonganim.drama.love.data.remote.dto.RegisterRequest
import com.truonganim.drama.love.data.remote.dto.toDomain
import com.truonganim.drama.love.domain.model.User
import com.truonganim.drama.love.domain.model.CheckInReward
import com.truonganim.drama.love.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
) : UserRepository {

    private val _localUser = MutableStateFlow<User?>(null)

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val authResponse = response.body()
                if (authResponse != null) {
                    val user = authResponse.user.toDomain()
                    _localUser.value = user
                    // TODO: Save token to preferences
                    Result.success(user)
                } else {
                    Result.failure(Exception("Login response is null"))
                }
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(username: String, email: String, password: String): Result<User> {
        return try {
            val response = apiService.register(RegisterRequest(username, email, password))
            if (response.isSuccessful) {
                val authResponse = response.body()
                if (authResponse != null) {
                    val user = authResponse.user.toDomain()
                    _localUser.value = user
                    // TODO: Save token to preferences
                    Result.success(user)
                } else {
                    Result.failure(Exception("Register response is null"))
                }
            } else {
                Result.failure(Exception("Registration failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            val response = apiService.logout()
            if (response.isSuccessful) {
                _localUser.value = null
                // TODO: Clear token from preferences
                Result.success(Unit)
            } else {
                Result.failure(Exception("Logout failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Even if API call fails, clear local data
            _localUser.value = null
            Result.success(Unit)
        }
    }

    override suspend fun getCurrentUser(): Result<User?> {
        return try {
            val response = apiService.getCurrentUser()
            if (response.isSuccessful) {
                val user = response.body()?.toDomain()
                _localUser.value = user
                Result.success(user)
            } else {
                Result.failure(Exception("Failed to get current user: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Return local user if API fails
            Result.success(_localUser.value)
        }
    }

    override suspend fun updateProfile(user: User): Result<User> {
        return try {
            // TODO: Convert User to UserDto
            val response = apiService.updateProfile(
                com.truonganim.drama.love.data.remote.dto.UserDto(
                    id = user.id,
                    username = user.username,
                    email = user.email,
                    avatarUrl = user.avatarUrl,
                    points = user.points,
                    consecutiveCheckInDays = user.consecutiveCheckInDays,
                    lastCheckInDate = user.lastCheckInDate,
                    favoriteVideos = user.favoriteVideos,
                    watchHistory = user.watchHistory,
                    createdAt = user.createdAt,
                    updatedAt = user.updatedAt
                )
            )
            if (response.isSuccessful) {
                val updatedUser = response.body()?.toDomain()
                if (updatedUser != null) {
                    _localUser.value = updatedUser
                    Result.success(updatedUser)
                } else {
                    Result.failure(Exception("Update response is null"))
                }
            } else {
                Result.failure(Exception("Profile update failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateAvatar(avatarUrl: String): Result<Unit> {
        return try {
            val response = apiService.updateAvatar(mapOf("avatar_url" to avatarUrl))
            if (response.isSuccessful) {
                // Update local user avatar
                _localUser.value = _localUser.value?.copy(avatarUrl = avatarUrl)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Avatar update failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun dailyCheckIn(): Result<CheckInReward> {
        return try {
            val response = apiService.dailyCheckIn()
            if (response.isSuccessful) {
                val reward = response.body()?.toDomain()
                if (reward != null) {
                    // Update local user points and consecutive days
                    _localUser.value = _localUser.value?.copy(
                        points = _localUser.value!!.points + reward.points + reward.bonusPoints,
                        consecutiveCheckInDays = reward.consecutiveDays,
                        lastCheckInDate = System.currentTimeMillis()
                    )
                    Result.success(reward)
                } else {
                    Result.failure(Exception("Check-in response is null"))
                }
            } else {
                Result.failure(Exception("Daily check-in failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Return dummy reward for testing
            val dummyReward = CheckInReward(
                points = 10,
                bonusPoints = 5,
                consecutiveDays = (_localUser.value?.consecutiveCheckInDays ?: 0) + 1,
                message = "Điểm danh thành công!"
            )
            Result.success(dummyReward)
        }
    }

    override suspend fun getCheckInStatus(): Result<Boolean> {
        return try {
            val response = apiService.getCheckInStatus()
            if (response.isSuccessful) {
                val status = response.body()?.get("can_check_in") ?: false
                Result.success(status)
            } else {
                Result.failure(Exception("Failed to get check-in status: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Return true for testing
            Result.success(true)
        }
    }

    override suspend fun getUserPoints(): Result<Int> {
        return try {
            val response = apiService.getUserPoints()
            if (response.isSuccessful) {
                val points = response.body()?.get("points") ?: 0
                Result.success(points)
            } else {
                Result.failure(Exception("Failed to get user points: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Return local points or default
            Result.success(_localUser.value?.points ?: 0)
        }
    }

    override suspend fun addPoints(points: Int): Result<Unit> {
        return try {
            val response = apiService.addPoints(mapOf("points" to points))
            if (response.isSuccessful) {
                // Update local user points
                _localUser.value = _localUser.value?.copy(
                    points = (_localUser.value?.points ?: 0) + points
                )
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to add points: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deductPoints(points: Int): Result<Unit> {
        return try {
            val response = apiService.deductPoints(mapOf("points" to points))
            if (response.isSuccessful) {
                // Update local user points
                _localUser.value = _localUser.value?.copy(
                    points = maxOf(0, (_localUser.value?.points ?: 0) - points)
                )
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to deduct points: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getLocalUser(): Flow<User?> {
        return _localUser.asStateFlow()
    }

    override suspend fun saveLocalUser(user: User) {
        _localUser.value = user
        // TODO: Save to local storage/preferences
    }

    override suspend fun clearLocalUser() {
        _localUser.value = null
        // TODO: Clear from local storage/preferences
    }
}
