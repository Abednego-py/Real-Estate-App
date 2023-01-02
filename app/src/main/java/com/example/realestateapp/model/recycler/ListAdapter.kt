package com.example.realestateapp.model.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateapp.R
import com.example.realestateapp.model.CellClickListener
import com.example.realestateapp.model.Home

class ListAdapter(
    private val dataSet: List<Home>,
    private val cellClickListener: CellClickListener
    ) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView

            init {
                // Define click listener for the ViewHolder's View.
                imageView = view.findViewById(R.id.imageView5)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.list_item_layout, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            val item = dataSet[position]
            viewHolder.imageView.setImageResource(item.imageResourceId)
            viewHolder.itemView.setOnClickListener {
                cellClickListener.onCellClickListener(item)
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

    }