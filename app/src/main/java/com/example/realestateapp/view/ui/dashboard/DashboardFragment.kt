package com.example.realestateapp.view.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.realestateapp.databinding.FragmentDashboardBinding
import com.example.realestateapp.model.Datasource
import com.example.realestateapp.model.recycler.HomeAdapter
import com.example.realestateapp.model.recycler.TagsAdapter
import kotlinx.android.synthetic.main.activity_login.*


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val myDataset = Datasource().loadImages()

        val recyclerView = binding.recylerList
        recyclerView.adapter =  TagsAdapter(myDataset)

        recyclerView.setHasFixedSize(true)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}