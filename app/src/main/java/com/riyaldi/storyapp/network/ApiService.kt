package com.riyaldi.storyapp.network

import com.riyaldi.storyapp.model.login.LoginResponse
import com.riyaldi.storyapp.model.signup.SignUpResponse
import com.riyaldi.storyapp.model.stories.StoriesResponse
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
}