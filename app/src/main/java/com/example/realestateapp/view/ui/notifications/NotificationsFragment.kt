package com.example.realestateapp.view.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestateapp.databinding.FragmentNotificationsBinding
import com.example.realestateapp.model.recycler.SearchAdapter
import com.example.realestateapp.model.search.data.SearchApplication
import com.example.realestateapp.viewmodels.SearchItemViewModel
import com.example.realestateapp.viewmodels.SearchViewModelFactory


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var searchAdapter : SearchAdapter

    private val viewModel: SearchItemViewModel by activityViewModels {
        SearchViewModelFactory(
            (activity?.application as SearchApplication).database
                .searchDao()
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = SearchAdapter()

        val recyclerView = binding.recylerList
        recyclerView.adapter = searchAdapter
        recyclerView.setHasFixedSize(true)

        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
        viewModel.allSearchItems.observe(viewLifecycleOwner){
            searchAdapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}