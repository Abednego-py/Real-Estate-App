package com.example.realestateapp.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.realestateapp.view.LoginActivity
import com.example.realestateapp.view.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeController {
    private lateinit var auth: FirebaseAuth

    fun login(currentClass: AppCompatActivity, nextClass: Class<LoginActivity>) : Intent {
        val intent = Intent(currentClass, nextClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        return intent
    }

    fun checkUser(): FirebaseUser? {
        // Initialize Firebase Auth
        auth = Firebase.auth
        // Check if user is signed in (non-null) and update UI accordingly.
        return auth.currentUser
    }
    fun signUp(currentClass : AppCompatActivity, nextClass : Class<SignUpActivity>) : Intent{
        val intent = Intent(currentClass, nextClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        return intent
    }
}