package com.example.realestateapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.realestateapp.R

class LoginSuccessful : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_successful)

        Handler().postDelayed(Runnable {
            val mainIntent = Intent(this@LoginSuccessful, HomeActivity::class.java)
            this@LoginSuccessful.startActivity(mainIntent)
            this@LoginSuccessful.finish()
        }, 5000)
    }
}