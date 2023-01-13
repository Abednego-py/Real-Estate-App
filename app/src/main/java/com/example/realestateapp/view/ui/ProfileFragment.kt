package com.example.realestateapp.view.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.realestateapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Initialize Firebase Auth
        auth = Firebase.auth

//        val user = Firebase.auth.currentUser
//        user?.let {
//            // Name, email address, and profile photo Url
//            val name = user.displayName
//            val email = user.email
//            val photoUrl = user.photoUrl
//
//            // Check if user's email is verified
//            val emailVerified = user.isEmailVerified
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getToken() instead.
//            val uid = user.uid
//
//            binding.imageView7.setImageURI(photoUrl)
//            binding.editTextTextPersonName.setText(name)
//            binding.editTextTextEmailAddress.setText(email)
//
//        }
        val user2 = Firebase.auth.currentUser
        user2?.let {
            for (profile in it.providerData) {
                // Id of the provider (ex: google.com)
                val providerId = profile.providerId

                // UID specific to the provider
                val uid = profile.uid

                // Name, email address, and profile photo Url
                val name = profile.displayName
                val email = profile.email
                val photoUrl = profile.photoUrl
                binding.imageView7.setImageURI(photoUrl)
                binding.editTextTextPersonName.setText(name)
                binding.editTextTextEmailAddress.setText(email)

            }
        }

        binding.materialCardViewButton.setOnClickListener {
            val user = Firebase.auth.currentUser

            user!!.updateEmail(binding.editTextTextEmailAddress.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User email address updated.")
                        Toast.makeText(requireContext(), "User email updated", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        return binding.root
    }

}