package com.riyaldi.storyapp.data.remote.response.signup


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)