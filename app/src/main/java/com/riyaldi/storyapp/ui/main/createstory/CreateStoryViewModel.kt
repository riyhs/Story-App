package com.riyaldi.storyapp.ui.main.createstory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.model.stories.PostStoryResponse
import com.riyaldi.storyapp.network.ApiConfig
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateStoryViewModel: ViewModel() {
    companion object{
        private const val TAG = "CreateStoryViewModel"
    }

    private val _createStoryResponse = MutableLiveData<PostStoryResponse>()
    val createStoryResponse: LiveData<PostStoryResponse> = _createStoryResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun postStory(file: MultipartBody.Part, description: RequestBody, token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService(token).postStory(file, description)
        client.enqueue(object : Callback<PostStoryResponse> {
            override fun onResponse(call: Call<PostStoryResponse>, response: Response<PostStoryResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _createStoryResponse.value = response.body()
                } else {
                    _createStoryResponse.value = response.body()
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostStoryResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}