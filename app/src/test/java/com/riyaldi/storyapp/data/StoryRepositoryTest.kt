package com.riyaldi.storyapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.riyaldi.storyapp.data.remote.network.ApiService
import com.riyaldi.storyapp.utils.DataDummy
import com.riyaldi.storyapp.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoryRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = FakeApiService()
    }

    @Test
    fun getStories() = runTest {
        val expectedResponse = DataDummy.generateDummyStories()
        val actualResponse = apiService.getStories(1, 15)
        assertNotNull(actualResponse)
        assertEquals(expectedResponse.listStory.size, actualResponse.listStory.size)
    }

    @Test
    fun getStoriesWithLocation() = runTest {
        val expectedResponse = DataDummy.generateDummyStories()
        val actualResponse = apiService.getStories(1, 15)
        assertNotNull(actualResponse)
        assertEquals(expectedResponse.listStory.size, actualResponse.listStory.size)
    }

    @Test
    fun postStory() = runTest {
        val descriptionText = "Description text"
        val description = descriptionText.toRequestBody("text/plain".toMediaType())

        val file = Mockito.mock(File::class.java)
        val requestImageFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo name",
            "photo.jpg",
            requestImageFile
        )

        val expectedResponse = DataDummy.generateDummyCreateStory()
        val actualResponse = apiService.postStory(imageMultipart, description)
        assertNotNull(actualResponse)
        assertEquals(expectedResponse.message, actualResponse.message)
    }

    @Test
    fun postSignUp() = runTest {
        val expectedResponse = DataDummy.generateDummyRegister()
        val actualResponse = apiService.postSignUp("Name", "name@mail.com", "secretPassword")
        assertNotNull(actualResponse)
        assertEquals(expectedResponse.message, actualResponse.message)
    }

    @Test
    fun postLogin() = runTest {
        val expectedResponse = DataDummy.generateDummyLogin()
        val actualResponse = apiService.postLogin("name@mail.com", "secretPassword")
        assertNotNull(actualResponse)
        assertEquals(expectedResponse.message, actualResponse.message)
    }
}