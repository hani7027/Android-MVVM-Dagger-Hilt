package com.hk.reciptmanagment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hk.reciptmanagment.R
import com.hk.reciptmanagment.data.viewModel.LoginViewModel
import com.hk.reciptmanagment.databinding.ActivityLoginBinding
import com.hk.reciptmanagment.utils.Responce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.setLifecycleOwner(this)

        binding.loginViewModel = loginViewModel
        loginViewModel.logInStatus.observe(this, {
            when (it) {
                is Responce.Success -> {
                    var intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        binding.registorNow.setOnClickListener {
            var intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}