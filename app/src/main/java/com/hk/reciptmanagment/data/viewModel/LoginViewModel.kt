package com.hk.reciptmanagment.data.viewModel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.hk.reciptmanagment.data.room.dao.UserDao
import com.hk.reciptmanagment.model.User
import com.hk.reciptmanagment.utils.Constants
import com.hk.reciptmanagment.utils.FormErrors
import com.hk.reciptmanagment.utils.Responce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(var userDao: UserDao,val sharedPreferences: SharedPreferences) : ViewModel() {
    var showPd = MutableLiveData(false)
    var userName: MutableLiveData<String> = MutableLiveData("")
    var password = MutableLiveData("")
    val formErrors = ObservableArrayList<FormErrors>()
    private val _logInStatus = MutableLiveData<Responce<User>>()
    val logInStatus: LiveData<Responce<User>>
        get() = _logInStatus

    fun loginNow() {
        if (isFormValid()) {
            viewModelScope.launch {
                showPd.postValue(true)
                val result = userDao.login(userName.value!!, password.value!!)
                if (result.size > 0) {
                    _logInStatus.postValue(Responce.Success(result.get(0)))
                    sharedPreferences.edit().putString(Constants.USER_DETAILS, Gson().toJson(result.get(0)))

                } else {
                    _logInStatus.postValue(Responce.Error("Invalid username or password"))
                }
                showPd.postValue(false)
            }
        }
    }


    fun isFormValid(): Boolean {
        formErrors.clear()
        if (userName.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.INVALID_USERNAME)
        }
        if (password.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.INVALID_PASSWORD)
        }
        return formErrors.isEmpty()
    }
}