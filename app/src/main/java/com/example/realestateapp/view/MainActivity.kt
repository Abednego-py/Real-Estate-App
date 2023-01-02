package com.example.realestateapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.realestateapp.controller.HomeController
import com.example.realestateapp.databinding.ActivityMainBinding
import com.example.realestateapp.databinding.ContentMainBinding



class MainActivity : AppCompatActivity() {

    val homeController = HomeController()
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ContentMainBinding
    private var isBackPressed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding2 = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        binding2.login.setOnClickListener {
            intent = homeController.login(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding2.signup.setOnClickListener {
            intent = homeController.signUp(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
//    public override fun onStart() {
//        super.onStart()
//        val currentUser = homeController.checkUser()
//        if (currentUser != null) {
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            startActivity(intent)
//        }
//    }

    override fun onBackPressed() {

        if (isBackPressed) {
            super.onBackPressed()
            return
        }
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
        isBackPressed = true

        Handler().postDelayed(Runnable {
            fun run() {
                isBackPressed = false
            }
        }, 2000)
    }}