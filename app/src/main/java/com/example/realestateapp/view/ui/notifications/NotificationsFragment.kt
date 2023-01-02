package com.example.realestateapp.view.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.realestateapp.databinding.FragmentNotificationsBinding
import com.example.realestateapp.model.Datasource
import com.example.realestateapp.model.DemoSearchData
import com.example.realestateapp.model.recycler.HomeAdapter
import com.example.realestateapp.model.recycler.SearchAdapter
import kotlinx.android.synthetic.main.activity_login.*


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val myDataset = DemoSearchData().loadText()

        val recyclerView = binding.recylerList
        recyclerView.adapter = SearchAdapter(myDataset)

        recyclerView.setHasFixedSize(true)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}