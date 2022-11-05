package com.riyaldi.storyapp.ui.map

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.data.remote.network.ApiConfig
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel: ViewModel() {
    companion object{
        private const val TAG = "ListStoryViewModel"
    }

    private val _storiesResponse = MutableLiveData<StoriesResponse>()
    private val storiesResponse: LiveData<StoriesResponse> = _storiesResponse

    fun setStories(context: Context) {
        val client = ApiConfig.getApiService(context).getStoriesWithLocation(1)
        client.enqueue(object : Callback<StoriesResponse> {
            override fun onResponse(call: Call<StoriesResponse>, response: Response<StoriesResponse>) {
                if (response.isSuccessful) {
                    _storiesResponse.value = response.body()
                } else {
                    _storiesResponse.value = response.body()
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getStories(): LiveData<StoriesResponse> {
        return storiesResponse
    }
}