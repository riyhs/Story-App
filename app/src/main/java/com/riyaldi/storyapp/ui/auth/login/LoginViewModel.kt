package com.riyaldi.storyapp.ui.auth.login

import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.data.StoryRepository

class LoginViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun login(email: String, password: String) = storyRepository.postLogin(email, password)
}