package com.komissarov.tasktracker.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.login.LoginActivity
import com.komissarov.tasktracker.mainpage.MainActivity
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SplashViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)


        viewModel.isLogged.observe(this) {
            val intent = if (it) {
                MainActivity.provideScreen(this)
            } else {
                LoginActivity.provideScreen(this)
            }
            startActivity(intent)
            finish()
        }
        viewModel.getLogged()
    }
}