package com.riyaldi.storyapp.ui.main.createstory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.riyaldi.storyapp.data.Result
import com.riyaldi.storyapp.data.StoryRepository
import com.riyaldi.storyapp.data.remote.response.stories.PostStoryResponse
import com.riyaldi.storyapp.utils.DataDummy
import com.riyaldi.storyapp.utils.getOrAwaitValue
import io.github.serpro69.kfaker.Faker
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateStoryViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var createStoryViewModel: CreateStoryViewModel
    private val dummyResponse = DataDummy.generateDummyCreateStory()

    @Before
    fun setUp() {
        createStoryViewModel = CreateStoryViewModel(storyRepository)
    }

    @Test
    fun `when postStory Should Not Null and return success`() {
        val faker = Faker()
        val descriptionText = faker.lorem

        val description = descriptionText.toString().toRequestBody("text/plain".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            "photo",
        )

        val expectedPostResponse = MutableLiveData<Result<PostStoryResponse>>()
        expectedPostResponse.value = Result.Success(dummyResponse)

        Mockito.`when`(storyRepository.postStory(imageMultipart, description)).thenReturn(expectedPostResponse)

        val actualResponse = createStoryViewModel.postStory(imageMultipart, description).getOrAwaitValue()
        Mockito.verify(storyRepository).postStory(imageMultipart, description)
        assertNotNull(actualResponse)
        assertTrue(actualResponse is Result.Success)
        assertEquals(dummyResponse.error, (actualResponse as Result.Success).data.error)
    }

    @Test
    fun `when Network Error Should Return Error`() {
        val faker = Faker()
        val descriptionText = faker.lorem

        val description = descriptionText.toString().toRequestBody("text/plain".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            "photo",
        )

        val expectedPostResponse = MutableLiveData<Result<PostStoryResponse>>()
        expectedPostResponse.value = Result.Error("network error")

        Mockito.`when`(storyRepository.postStory(imageMultipart, description)).thenReturn(expectedPostResponse)

        val actualResponse = createStoryViewModel.postStory(imageMultipart, description).getOrAwaitValue()
        Mockito.verify(storyRepository).postStory(imageMultipart, description)
        assertNotNull(actualResponse)
        assertTrue(actualResponse is Result.Error)
    }
}
