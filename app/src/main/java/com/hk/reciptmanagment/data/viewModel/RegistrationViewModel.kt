package com.hk.reciptmanagment.data.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
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
class RegistrationViewModel @Inject constructor(val userDao: UserDao) : ViewModel() {

    var showPd = MutableLiveData(false)
    var user = MutableLiveData(User())
    var confirmPassword = MutableLiveData<String>()
    val formErrors = ObservableArrayList<FormErrors>()

    private val _registrationStatus = MutableLiveData<Responce<User>>()
    val registrationStatus: LiveData<Responce<User>>
        get() = _registrationStatus


    fun onRegistration() {
        Log.e("register", "registor")

        if (isFormValid()) {
            viewModelScope.launch {
                showPd.postValue(true)
                try {
                    userDao.insert(user.value)
                    _registrationStatus.postValue(Responce.Success(user.value))
                } catch (e: Exception) {
                    _registrationStatus.postValue(Responce.Error(e.message))
                }
                showPd.postValue(false)

            }
        }
    }


    fun isFormValid(): Boolean {
        formErrors.clear()

        if (user.value?.firstName.isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_FIRST_NAME)
        }
        if (user.value?.lastName.isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_LAST_NAME)
        }
        if (user.value?.phoneNumber.isNullOrEmpty()) {
            formErrors.add(FormErrors.INVALID_PHONE_NUMBER)
        }
        if (user.value?.userName.isNullOrEmpty()) {
            formErrors.add(FormErrors.INVALID_USERNAME)
        }
        if (user.value?.password.isNullOrEmpty()) {
            formErrors.add(FormErrors.INVALID_PASSWORD)
        } else {
            if (!user.value!!.password.equals(confirmPassword.value)) {
                formErrors.add(FormErrors.PASSWORDS_NOT_MATCHING)
            }
        }
        return formErrors.isEmpty()
    }


}