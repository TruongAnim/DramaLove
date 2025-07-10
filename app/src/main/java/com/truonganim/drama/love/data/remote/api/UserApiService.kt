package com.truonganim.drama.love.data.remote.api

import com.truonganim.drama.love.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface UserApiService {
    
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>
    
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<AuthResponse>
    
    @POST("auth/logout")
    suspend fun logout(): Response<Unit>
    
    @GET("user/profile")
    suspend fun getCurrentUser(): Response<UserDto>
    
    @PUT("user/profile")
    suspend fun updateProfile(
        @Body user: UserDto
    ): Response<UserDto>
    
    @PUT("user/avatar")
    suspend fun updateAvatar(
        @Body request: Map<String, String>
    ): Response<Unit>
    
    @POST("user/checkin")
    suspend fun dailyCheckIn(): Response<CheckInRewardDto>
    
    @GET("user/checkin/status")
    suspend fun getCheckInStatus(): Response<Map<String, Boolean>>
    
    @GET("user/points")
    suspend fun getUserPoints(): Response<Map<String, Int>>
    
    @POST("user/points/add")
    suspend fun addPoints(
        @Body request: Map<String, Int>
    ): Response<Unit>
    
    @POST("user/points/deduct")
    suspend fun deductPoints(
        @Body request: Map<String, Int>
    ): Response<Unit>
}
