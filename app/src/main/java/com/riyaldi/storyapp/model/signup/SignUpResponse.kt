package com.riyaldi.storyapp.model.signup


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)