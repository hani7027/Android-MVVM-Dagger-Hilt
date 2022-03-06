package com.hk.reciptmanagment.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


object BindingAdaptoUtils {
    @JvmStatic
    @BindingAdapter("errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: String) {
        view.error = errorMessage
    }
}