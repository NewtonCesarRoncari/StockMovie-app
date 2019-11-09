package br.com.mov.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadThumbnail(
    endereco: String
) {
    Glide.with(this)
        .load(endereco)
        .centerCrop()
        .into(this)
}