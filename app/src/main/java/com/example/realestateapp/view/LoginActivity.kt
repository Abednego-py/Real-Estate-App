package com.example.realestateapp.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realestateapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val TAG = "LoginActivity"
    private lateinit var auth: FirebaseAuth
    private var pressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val password = binding.passwordText
        val email = binding.emailText
        val loading = binding.idPBLoading
        auth = Firebase.auth

        fun showLoader() {
                loading.visibility = View.VISIBLE
        }

        fun stopLoader(){
            loading.visibility = View.INVISIBLE
        }

        binding.loginUser.setOnClickListener {
            showLoader()
            if (isEntryValid(email.text.toString()) && isEntryValid(password.text.toString())) {
                email.error = "Please include your credentials"
                password.error = "Please include your credentials"
            }
            else if(isEntryValid(binding.emailText.text.toString())){
                email.error = "Please include your credentials"
            }
            else if(isEntryValid(binding.passwordText.text.toString())){
                password.error = "Please include your credentials"
            }
            else {
                auth.signInWithEmailAndPassword(
                    email.text.toString(),
                    password.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val intent = Intent(this, LoginSuccessful::class.java)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                            val user = auth.currentUser
//                        updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
//                        updateUI(null)
//                        }
                        }
                    }
            }
            stopLoader()
        }

        binding.createaccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    override fun onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            val alertDialog =   AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Do you want to exit the app?")
                .setCancelable(false)
                .setPositiveButton("Yes", null)
                .setNegativeButton("No", null)
                .show()
            val mPositiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            mPositiveButton.setOnClickListener {
                exitProcess(0)
            }
        }
        pressedTime = System.currentTimeMillis();
    }

    fun isEntryValid(entry: String): Boolean {
        return entry.isEmpty()
    }
}