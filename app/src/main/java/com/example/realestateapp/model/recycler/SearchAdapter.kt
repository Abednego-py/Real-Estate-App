package com.example.realestateapp.model.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateapp.databinding.SearchItemsLayoutBinding
import com.example.realestateapp.model.search.data.SearchItems

class SearchAdapter() :
    ListAdapter<SearchItems, SearchAdapter.SearchItemViewHolder>(DiffCallback) {

    class SearchItemViewHolder(private var binding : SearchItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(searchitem : SearchItems){
            binding.apply{
                searchTitle.text = searchitem.searchText

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            SearchItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val current = getItem(position)
//        holder.itemView.setOnClickListener {
//            onItemClicked(current)
//        }
        holder.bind(current)
    }

        private object DiffCallback : DiffUtil.ItemCallback<SearchItems>(){
            override fun areItemsTheSame(oldItem: SearchItems, newItem: SearchItems): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SearchItems, newItem: SearchItems): Boolean {
                return oldItem.searchText == newItem.searchText
            }


    }

}
