package com.komissarov.tasktracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komissarov.tasktracker.login.LoginActivity
import com.komissarov.tasktracker.mainpage.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val logged = true//todo прицепить sessionManager

        val intent = if(logged){
            Intent(this, MainActivity::class.java)
        }else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}