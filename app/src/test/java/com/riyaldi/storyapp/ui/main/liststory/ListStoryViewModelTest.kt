package com.riyaldi.storyapp.ui.main.liststory

import com.riyaldi.storyapp.data.StoryRepository
import com.riyaldi.storyapp.utils.DataDummy
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListStoryViewModelTest{

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var listStoryViewModel: ListStoryViewModel
    private val dummyStoriesResponse = DataDummy.generateDummyStories()
}