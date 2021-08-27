package com.example.submission2belajarfundamentalaplikasiandroid.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission2belajarfundamentalaplikasiandroid.R

@BindingAdapter("avatar")
fun Avatar(imageView: ImageView, avatar:String)=
    Glide.with(imageView)
        .load(avatar)
        .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_user_24))
        .into(imageView)