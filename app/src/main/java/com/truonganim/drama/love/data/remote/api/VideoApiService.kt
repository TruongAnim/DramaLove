package com.truonganim.drama.love.data.remote.api

import com.truonganim.drama.love.data.remote.dto.VideoDto
import retrofit2.Response
import retrofit2.http.*

interface VideoApiService {
    
    @GET("videos/hot")
    suspend fun getHotVideos(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<VideoDto>>
    
    @GET("videos/new")
    suspend fun getNewVideos(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<VideoDto>>
    
    @GET("videos/favorites")
    suspend fun getFavoriteVideos(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<VideoDto>>
    
    @GET("videos/{id}")
    suspend fun getVideoById(
        @Path("id") videoId: String
    ): Response<VideoDto>
    
    @GET("videos/category/{categoryId}")
    suspend fun getVideosByCategory(
        @Path("categoryId") categoryId: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<VideoDto>>
    
    @GET("videos/search")
    suspend fun searchVideos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<VideoDto>>
    
    @POST("videos/{id}/like")
    suspend fun likeVideo(
        @Path("id") videoId: String
    ): Response<Unit>
    
    @POST("videos/{id}/dislike")
    suspend fun dislikeVideo(
        @Path("id") videoId: String
    ): Response<Unit>
    
    @POST("videos/{id}/favorite")
    suspend fun addToFavorites(
        @Path("id") videoId: String
    ): Response<Unit>
    
    @DELETE("videos/{id}/favorite")
    suspend fun removeFromFavorites(
        @Path("id") videoId: String
    ): Response<Unit>
    
    @POST("videos/{id}/share")
    suspend fun shareVideo(
        @Path("id") videoId: String
    ): Response<Unit>
    
    @POST("videos/{id}/view")
    suspend fun recordView(
        @Path("id") videoId: String
    ): Response<Unit>
}
