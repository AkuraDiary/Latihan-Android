package com.example.submission2belajarfundamentalaplikasiandroid.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.request.RequestOptions
import com.example.submission2belajarfundamentalaplikasiandroid.R
import com.example.submission2belajarfundamentalaplikasiandroid.others.GlideApp

@BindingAdapter("avatar")
fun avatar(imageView: ImageView, avatar:String)=
    GlideApp.with(imageView.context)
        .load(avatar)
        .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_user_24))
        .into(imageView)