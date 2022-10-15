package com.riyaldi.storyapp.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.model.login.LoginResponse
import com.riyaldi.storyapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    companion object{
        private const val TAG = "LoginViewModel"
    }

    private val _isLoginFailed = MutableLiveData<Boolean>()
    val isLoginFailed: LiveData<Boolean> = _isLoginFailed

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun postLogin(email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().postLogin(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _isLoginFailed.value = response.body()?.error
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

//            name=riyaldi&email=riyaldi%40gmail.com&password=helloworld
//            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLU03MnRBR3Bab3dvZm4zX0oiLCJpYXQiOjE2NjU4NjQ5NTN9.i50lxmamItKS2Vuo7tHeX3vXbMtX7TylYvRubTwezUs

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}