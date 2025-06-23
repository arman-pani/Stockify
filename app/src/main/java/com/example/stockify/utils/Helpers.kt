package com.example.stockify.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stockify.R

fun ImageView.loadImage(url: String?, placeholderResId: Int = R.drawable.logo_placeholder) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions()
            .placeholder(placeholderResId)
            .error(placeholderResId)
            .circleCrop())
        .into(this)
}
