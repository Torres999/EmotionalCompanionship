package com.emotional.companionship.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .into(view)
    }
} 