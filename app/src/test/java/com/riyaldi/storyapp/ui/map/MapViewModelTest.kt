package com.riyaldi.storyapp.ui.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.riyaldi.storyapp.data.Result
import com.riyaldi.storyapp.data.StoryRepository
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import com.riyaldi.storyapp.utils.DataDummy
import com.riyaldi.storyapp.utils.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var mapViewModel: MapViewModel
    private val dummyStoriesResponse = DataDummy.generateDummyStories()

    @Before
    fun setUp() {
        mapViewModel = MapViewModel(storyRepository)
    }

    @Test
    fun `when getStoriesWithLocation Should Not Null and return success`() {
        val expectedStoryResponse = MutableLiveData<Result<StoriesResponse>>()
        expectedStoryResponse.value = Result.Success(dummyStoriesResponse)

        `when`(storyRepository.getStoriesWithLocation()).thenReturn(expectedStoryResponse)

        val actualStories = mapViewModel.getStoriesWithLocation().getOrAwaitValue()
        Mockito.verify(storyRepository).getStoriesWithLocation()
        Assert.assertNotNull(actualStories)
        Assert.assertTrue(actualStories is Result.Success)
        Assert.assertEquals(dummyStoriesResponse.listStory.size, (actualStories as Result.Success).data.listStory.size)
    }

    @Test
    fun `when Network Error Should Return Error`() {
        val expectedStoryResponse = MutableLiveData<Result<StoriesResponse>>()
        expectedStoryResponse.value = Result.Error("network error")

        `when`(storyRepository.getStoriesWithLocation()).thenReturn(expectedStoryResponse)

        val actualStories = mapViewModel.getStoriesWithLocation().getOrAwaitValue()
        Mockito.verify(storyRepository).getStoriesWithLocation()
        Assert.assertNotNull(actualStories)
        Assert.assertTrue(actualStories is Result.Error)
    }
}