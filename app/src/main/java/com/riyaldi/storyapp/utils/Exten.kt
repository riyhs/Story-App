package com.riyaldi.storyapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load

@BindingAdapter("app:loadImage")
fun loadImage(view : ImageView, url : String?){
    val drawable = CircularProgressDrawable(view.context)
    drawable.strokeWidth = 5f
    drawable.centerRadius = 30f
    drawable.start()

    view.load(url) {
        placeholder(drawable)
        allowHardware(false)
    }
}