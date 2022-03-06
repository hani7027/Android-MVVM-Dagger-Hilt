package com.hk.reciptmanagment.data.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.hk.reciptmanagment.data.room.dao.UserDao
import com.hk.reciptmanagment.model.User
import com.hk.reciptmanagment.utils.Constants
import com.hk.reciptmanagment.utils.Responce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel @Inject constructor(userDao: UserDao) : ViewModel() {
    var showPd = MutableLiveData(false)

}