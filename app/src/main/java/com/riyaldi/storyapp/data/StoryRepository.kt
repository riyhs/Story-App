package com.riyaldi.storyapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.riyaldi.storyapp.data.remote.network.ApiService
import com.riyaldi.storyapp.data.remote.response.stories.PostStoryResponse
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import com.riyaldi.storyapp.data.remote.response.stories.Story
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryRepository(private val apiService: ApiService) {
    fun getStories(): LiveData<PagingData<Story>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService)
            }
        ).liveData
    }

    fun getStoriesWithLocation(): LiveData<Result<StoriesResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getStoriesWithLocation(1)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("ListStoryViewModel", "getStoriesWithLocation: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postStory(file: MultipartBody.Part, description: RequestBody): LiveData<Result<PostStoryResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.postStory(file, description)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("CreateStoryViewModel", "postStory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
}