package com.riyaldi.storyapp.ui.auth.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.model.signup.SignUpResponse
import com.riyaldi.storyapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    companion object{
        private const val TAG = "SignUpViewModel"
    }

    private val _isSignUpFailed = MutableLiveData<Boolean>()
    val isSignUpFailed: LiveData<Boolean> = _isSignUpFailed

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun postSignUp(name: String, email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().postSignUp(name, email, password)
        client.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _isSignUpFailed.value = response.body()?.error
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

//            name=riyaldi&email=riyaldi%40gmail.com&password=helloworld

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}