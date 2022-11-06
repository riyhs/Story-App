package com.riyaldi.storyapp.ui.auth.signup

import androidx.lifecycle.ViewModel
import com.riyaldi.storyapp.data.StoryRepository

class SignUpViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun signUp(name: String, email: String, password: String) = storyRepository.postSignUp(name, email, password)
}