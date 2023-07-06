package com.technource.android.ui.moreModule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin_boilerplate.databinding.MoreListItemBinding
import com.technource.android.commonInterface.RecyclerviewInterface


class MoreListAdapter : RecyclerView.Adapter<MoreListViewHolder>() {
    private val moreList: MutableList<MoreModel> = mutableListOf()
    private lateinit var setOnItemClick: RecyclerviewInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreListViewHolder {
        // Inflate the layout for the item view
        val inflater = LayoutInflater.from(parent.context)
        val binding = MoreListItemBinding.inflate(inflater, parent, false)
        return MoreListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoreListViewHolder, position: Int) {
        // Get the MoreModel at the current position
        val user = moreList[position]
        holder.bind(user)       // Bind the MoreModel to the ViewHolder

        // Set click listener for the item view
        holder.itemView.setOnClickListener {
            setOnItemClick.onItemClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return moreList.size
    }

    /**
    Sets the click listener for the RecyclerView item.
    @param onItemClick The click listener to be set.
     */
    fun setOnItemClick(onItemClick: RecyclerviewInterface) {
        setOnItemClick = onItemClick
    }

    /**
    Sets the data for the adapter.
    Clears the existing data in moreList and adds the new data from the provided list.
    Notifies the adapter that the data set has changed.
    @param more The list of MoreModel objects to be set as data.
     */
    fun setData(more: List<MoreModel>) {
        this.moreList.clear()
        this.moreList.addAll(more)
        notifyDataSetChanged()
    }
}

class MoreListViewHolder(private val binding: MoreListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(more: MoreModel) {
        // Bind the MoreModel to the item view
        binding.moreModel = more
        binding.executePendingBindings()
    }
}

