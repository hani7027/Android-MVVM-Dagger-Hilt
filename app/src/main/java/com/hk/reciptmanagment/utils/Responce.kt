package com.hk.reciptmanagment.utils

import android.widget.Toast
import com.hk.reciptmanagment.R
import dagger.hilt.android.qualifiers.ApplicationContext

sealed class Responce<T>(val data: T? = null, val message: String? = null) {

    class Message<T> : Responce<T>()
    class Success<T>(data: T? = null) : Responce<T>(data = data)
    class Error<T>(message: String? = null) : Responce<T>(message = message) {
        init {
            Toast.makeText(
                App.appContext,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}