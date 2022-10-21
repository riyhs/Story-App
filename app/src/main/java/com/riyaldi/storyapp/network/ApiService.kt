package com.riyaldi.storyapp.network

import com.riyaldi.storyapp.model.login.LoginResponse
import com.riyaldi.storyapp.model.signup.SignUpResponse
import com.riyaldi.storyapp.model.stories.PostStoryResponse
import com.riyaldi.storyapp.model.stories.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun postSignUp(
        @Field("name") name: String,
        @Field("email") enail: String,
        @Field("password") password: String
    ): Call<SignUpResponse>

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") enail: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    fun getStories() : Call<StoriesResponse>

    @Multipart
    @POST("stories")
    fun postStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<PostStoryResponse>
}