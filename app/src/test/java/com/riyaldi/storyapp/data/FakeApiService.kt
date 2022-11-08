package com.riyaldi.storyapp.data

import com.riyaldi.storyapp.data.remote.network.ApiService
import com.riyaldi.storyapp.data.remote.response.login.LoginResponse
import com.riyaldi.storyapp.data.remote.response.signup.SignUpResponse
import com.riyaldi.storyapp.data.remote.response.stories.PostStoryResponse
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import com.riyaldi.storyapp.utils.DataDummy
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FakeApiService: ApiService {
    override suspend fun postSignUp(name: String, enail: String, password: String): SignUpResponse {
        return DataDummy.generateDummyRegister()
    }

    override suspend fun postLogin(enail: String, password: String): LoginResponse {
        return DataDummy.generateDummyLogin()
    }

    override suspend fun getStories(page: Int, size: Int): StoriesResponse {
        return DataDummy.generateDummyStories()
    }

    override suspend fun getStoriesWithLocation(location: Int): StoriesResponse {
        return DataDummy.generateDummyStories()
    }

    override suspend fun postStory(
        file: MultipartBody.Part,
        description: RequestBody
    ): PostStoryResponse {
        return DataDummy.generateDummyCreateStory()

    }
}