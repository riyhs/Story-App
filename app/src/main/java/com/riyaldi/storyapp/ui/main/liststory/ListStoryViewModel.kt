package com.riyaldi.storyapp.ui.main.liststory

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.riyaldi.storyapp.data.StoryRepository
import com.riyaldi.storyapp.data.remote.response.stories.Story
import com.riyaldi.storyapp.di.Injection

class ListStoryViewModel(storyRepository: StoryRepository): ViewModel() {
    val stories: LiveData<PagingData<Story>> = storyRepository.getStories().cachedIn(viewModelScope)
}