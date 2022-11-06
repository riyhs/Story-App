package com.riyaldi.storyapp.ui.main.createstory

import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.data.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CreateStoryViewModel(private val storyRepository: StoryRepository): ViewModel() {
    fun postStory(file: MultipartBody.Part, description: RequestBody) = storyRepository.postStory(file, description)
}