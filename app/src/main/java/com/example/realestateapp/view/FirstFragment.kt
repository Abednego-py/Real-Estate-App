package com.example.realestateapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.realestateapp.R
import com.example.realestateapp.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        val paths = arrayOf("Gender" ,"male", "female")


        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item, paths
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinneritem.adapter =  adapter


        val email = binding.emailtext
        val firstName = binding.firstnametext.text.toString()
        val lastName = binding.lastnametext.text.toString()

        binding.next.setOnClickListener {
            if (isEntryValid(email.text.toString())) {
                email.error = "Email is required"
                email.hint = "Please enter a valid email"
            }
            else{
                val action = FirstFragmentDirections
                    .actionFirstFragmentToSecondFragment(email.text.toString())
                findNavController().navigate(action)
            }
        }

        binding.backbutton.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
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