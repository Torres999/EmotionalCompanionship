package com.emotional.companionship.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.emotional.companionship.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.ic_person)
            .into(view)
    } else {
        // 如果URL为空，加载默认图片
        Glide.with(view.context)
            .load(R.drawable.ic_person)
            .into(view)
    }
} 