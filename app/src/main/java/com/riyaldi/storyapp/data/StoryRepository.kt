package com.riyaldi.storyapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.riyaldi.storyapp.data.remote.network.ApiService
import com.riyaldi.storyapp.data.remote.response.stories.Story

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
}