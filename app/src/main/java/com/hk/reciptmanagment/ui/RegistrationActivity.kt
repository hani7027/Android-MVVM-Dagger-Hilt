package com.hk.reciptmanagment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hk.reciptmanagment.R
import com.hk.reciptmanagment.data.viewModel.RegistrationViewModel
import com.hk.reciptmanagment.databinding.ActivityRegistrationBinding
import com.hk.reciptmanagment.utils.Responce
import dagger.hilt.android.AndroidEntryPoint
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.edit
import com.hk.reciptmanagment.utils.Constants


@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private val viewModel: RegistrationViewModel by viewModels()
    lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel;

        viewModel.registrationStatus.observe(this) {
            when (it) {
                is Responce.Success -> {
                    Toast.makeText(applicationContext,"registered successfully",Toast.LENGTH_LONG).show()
                    onBackPressed()
                }
            }
        }
    }
}