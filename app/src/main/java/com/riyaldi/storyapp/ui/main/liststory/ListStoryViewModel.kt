package com.riyaldi.storyapp.ui.main.liststory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import com.riyaldi.storyapp.data.remote.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListStoryViewModel: ViewModel() {
    companion object{
        private const val TAG = "ListStoryViewModel"
    }

    private val _storiesResponse = MutableLiveData<StoriesResponse>()
    private val storiesResponse: LiveData<StoriesResponse> = _storiesResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setStories(token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).getStories()
        client.enqueue(object : Callback<StoriesResponse> {
            override fun onResponse(call: Call<StoriesResponse>, response: Response<StoriesResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _storiesResponse.value = response.body()
                } else {
                    _storiesResponse.value = response.body()
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getStories(): LiveData<StoriesResponse> {
        return storiesResponse
    }
}