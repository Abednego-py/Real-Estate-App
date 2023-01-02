package com.example.realestateapp.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.realestateapp.databinding.FragmentListBinding
import com.example.realestateapp.model.CellClickListener
import com.example.realestateapp.model.Datasource
import com.example.realestateapp.model.Home
import com.example.realestateapp.model.recycler.ListAdapter

class ListFragment : Fragment(), CellClickListener {
    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val myDataset = Datasource().loadImages()

        val recyclerView = binding.recylerList
        recyclerView.adapter = ListAdapter(myDataset, this)

        recyclerView.setHasFixedSize(true)

        return binding.root


    }

    override fun onCellClickListener(data: Home) {
        val action = ListFragmentDirections.actionListFragmentToItemFragment()
        findNavController().navigate(action)
    }

}