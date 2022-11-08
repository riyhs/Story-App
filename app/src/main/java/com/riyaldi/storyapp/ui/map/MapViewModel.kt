package com.riyaldi.storyapp.ui.map

import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.data.StoryRepository

class MapViewModel(private val storyRepository: StoryRepository): ViewModel() {
    fun getStoriesWithLocation() = storyRepository.getStoriesWithLocation()
}