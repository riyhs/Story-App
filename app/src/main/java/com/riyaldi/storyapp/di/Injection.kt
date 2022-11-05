package com.riyaldi.storyapp.di

import android.content.Context
import com.riyaldi.storyapp.data.StoryRepository
import com.riyaldi.storyapp.data.remote.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService(context)
        return StoryRepository(apiService)
    }
}