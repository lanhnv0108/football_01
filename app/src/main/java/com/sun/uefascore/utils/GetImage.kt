package com.sun.uefascore.utils

import android.os.AsyncTask
import android.widget.ImageView

fun getImage(view: ImageView, logo: String) {
    LoadImageUrl {
        view.setImageBitmap(it)
    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, logo)
}
