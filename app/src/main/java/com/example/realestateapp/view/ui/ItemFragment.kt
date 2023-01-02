package com.example.realestateapp.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.realestateapp.R
import com.example.realestateapp.controller.HomeController
import com.example.realestateapp.databinding.FragmentItemBinding
import com.example.realestateapp.databinding.FragmentListBinding
import com.example.realestateapp.view.MainActivity
import com.example.realestateapp.view.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ItemFragment : Fragment() {
    private var _binding: FragmentItemBinding? = null

    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    val homeController = HomeController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        val user = auth.currentUser
//            binding.contactSellerBtn.setOnClickListener {
//                    val intent = Intent(requireActivity(), MainActivity::class.java)
//                    startActivity(intent)
//        }
    }

}