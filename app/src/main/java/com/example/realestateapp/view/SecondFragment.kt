package com.example.realestateapp.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.realestateapp.R
import com.example.realestateapp.databinding.FragmentSecondBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    val args : SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        val paths = arrayOf("Account Type" ,"Savings", "Current")

        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item, paths)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinneritem.adapter =  adapter

        val password = binding.passwordText

        binding.button.setOnClickListener {
            binding.idPBLoading.visibility = View.VISIBLE
            if (isEntryValid(password.text.toString())) {
                password.error = "Password is required"
                password.hint =
                    "The password must be at least 8 characters long and include a number, " +
                            "lowercase letter, uppercase letter and special character"
            } else {
                auth.createUserWithEmailAndPassword(args.email, password.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val intent = Intent(requireActivity(), LoginSuccessful::class.java)
                            startActivity(intent)
                            val user = auth.currentUser
//                    updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
//                    updateUI(null)
                        }
                    }
//            }
            }
            binding.idPBLoading.visibility = View.GONE
        }

        binding.backbutton.setOnClickListener {
            val intent = Intent(requireActivity(), SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun isEntryValid(entry: String): Boolean {
        return entry.isEmpty()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}