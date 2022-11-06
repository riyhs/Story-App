package com.riyaldi.storyapp.data.remote.network

import com.riyaldi.storyapp.data.remote.response.login.LoginResponse
import com.riyaldi.storyapp.data.remote.response.signup.SignUpResponse
import com.riyaldi.storyapp.data.remote.response.stories.PostStoryResponse
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun postSignUp(
        @Field("name") name: String,
        @Field("email") enail: String,
        @Field("password") password: String
    ): SignUpResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("email") enail: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : StoriesResponse

    @GET("stories")
    suspend fun getStoriesWithLocation(
        @Query("location") location: Int,
    ) : StoriesResponse

    @Multipart
    @POST("stories")
    suspend fun postStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): PostStoryResponse
}