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

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ListStoryViewModel::class.java)) {
            return ListStoryViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

//class ListStoryViewModel(): ViewModel() {
//    companion object{
//        private const val TAG = "ListStoryViewModel"
//    }

//    private val _storiesResponse = MutableLiveData<StoriesResponse>()
//    private val storiesResponse: LiveData<StoriesResponse> = _storiesResponse

//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun setStories(token: String, location: Int = 0) {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService(token).getStories(location)
//        client.enqueue(object : Callback<StoriesResponse> {
//            override fun onResponse(call: Call<StoriesResponse>, response: Response<StoriesResponse>) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _storiesResponse.value = response.body()
//                } else {
//                    _storiesResponse.value = response.body()
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }

//    fun getStories(): LiveData<StoriesResponse> {
//        return storiesResponse
//    }
//}