package com.riyaldi.storyapp.ui.map

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.data.StoryRepository
import com.riyaldi.storyapp.data.remote.network.ApiConfig
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel(private val storyRepository: StoryRepository): ViewModel() {
    fun getStoriesWithLocation() = storyRepository.getStoriesWithLocation()
}