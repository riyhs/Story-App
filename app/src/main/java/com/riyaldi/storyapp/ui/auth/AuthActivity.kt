package com.riyaldi.storyapp.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riyaldi.storyapp.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportActionBar?.hide()
    }
}