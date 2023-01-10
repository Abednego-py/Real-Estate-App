package com.example.realestateapp.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.realestateapp.R
import com.example.realestateapp.databinding.FragmentHomeBinding
import com.example.realestateapp.model.CellClickListener
import com.example.realestateapp.model.Datasource
import com.example.realestateapp.model.Home
import com.example.realestateapp.model.recycler.HomeAdapter
import com.example.realestateapp.model.recycler.SearchAdapter
import com.example.realestateapp.model.search.data.SearchApplication
import com.example.realestateapp.model.search.data.SearchDatabase
import com.example.realestateapp.model.search.data.SearchItems
import com.example.realestateapp.model.tagClickListener
import com.example.realestateapp.viewmodels.SearchItemViewModel
import com.example.realestateapp.viewmodels.SearchViewModelFactory
import kotlinx.android.synthetic.main.item_layout.view.*


class HomeFragment : Fragment() , CellClickListener, tagClickListener {

    private var _binding: FragmentHomeBinding? = null

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

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val myDataset = Datasource().loadImages()

        val recyclerView = binding.recylerList
        recyclerView.adapter = HomeAdapter(myDataset, this, this)

        recyclerView.setHasFixedSize(true)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireContext(),
            SearchDatabase::class.java, "search_database"
        ).build()

        binding.textView14.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToListFragment()
            this.findNavController().navigate(action)
        }
        binding.searchHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchitem = SearchItems(searchText = query)

                searchitem.searchText?.let { viewModel.addNewSearchItem(it) }
//                val searchDao = db.searchDao()
//
//                lifecycleScope.launch {
//                    searchDao.create(searchitem)
//                }
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