package com.example.realestateapp.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.realestateapp.R
import com.example.realestateapp.databinding.FragmentHomeBinding
import com.example.realestateapp.model.CellClickListener
import com.example.realestateapp.model.Datasource
import com.example.realestateapp.model.Home
import com.example.realestateapp.model.recycler.HomeAdapter
import com.example.realestateapp.model.tagClickListener
import kotlinx.android.synthetic.main.item_layout.view.*


class HomeFragment : Fragment() , CellClickListener, tagClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val myDataset = Datasource().loadImages()

        val recyclerView = binding.recylerList
        recyclerView.adapter = HomeAdapter(myDataset, this, this)

        recyclerView.setHasFixedSize(true)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView14.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToListFragment()
            this.findNavController().navigate(action)
        }
        binding.searchHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are checking
                // if query exist or not.
//                if (programmingLanguagesList.contains(query)) {
//                    // if query exist within list we
//                    // are filtering our list adapter.
//                    listAdapter.filter.filter(query)
//                } else {
//                    // if query is not present we are displaying
//                    // a toast message as no  data found..
//                    Toast.makeText(requireContext(), "", Toast.LENGTH_LONG)
//                        .show()
//                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.
//                listAdapter.filter.filter(newText)

                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(data: Home) {
        val action = HomeFragmentDirections.actionNavigationHomeToItemFragment()
        findNavController().navigate(action)
    }

    override fun onTagClickListener() {
        Toast.makeText(requireContext(), "Item saved", Toast.LENGTH_SHORT).show()
        binding.root.imageView6.setImageResource(R.drawable.ic_baseline_bookmark_add_24_black)
    }
}