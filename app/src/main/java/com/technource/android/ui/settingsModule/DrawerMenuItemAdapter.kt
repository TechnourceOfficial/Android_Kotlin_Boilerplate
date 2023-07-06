package com.technource.android.ui.settingsModule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.DrawerMenuItemLayoutBinding
import com.technource.android.commonInterface.RecyclerviewInterface

class DrawerMenuItemAdapter(private var drawerMenu: List<DrawerMenu>) :
    RecyclerView.Adapter<DrawerMenuItemAdapter.DrawerMenuViewHolder>() {

    private lateinit var setOnItemClick: RecyclerviewInterface
    private var rowIndex = 0
    private var oldSelectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerMenuViewHolder {
        val view =
            DrawerMenuItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrawerMenuViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DrawerMenuViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.binding.itemName.text = drawerMenu[position].itemName
        holder.binding.itemDesc.text = drawerMenu[position].descText
        Glide.with(holder.itemView.context)
            .load(holder.itemView.context.getDrawable(drawerMenu[position].drawerIcon))
            .placeholder(R.drawable.ic_bullet)
            .into(holder.binding.itemIcon)

        holder.binding.mainLinearLayout.setOnClickListener {
            setOnItemClick.onItemClick(holder.adapterPosition)
            rowIndex = position
            notifyItemChanged(position)
            notifyItemChanged(oldSelectedPosition)
        }

        if (rowIndex == position) {
            oldSelectedPosition = rowIndex
            holder.binding.mainLinearLayout.setBackgroundDrawable(
                holder.binding.mainLinearLayout.context.resources.getDrawable(
                    R.drawable.edit_text_view_bg
                )
            )
            holder.binding.itemName.setTextColor(
                holder.binding.itemName.context.resources.getColor(
                    R.color.white
                )
            )
        } else {
            holder.binding.mainLinearLayout.setBackgroundColor(
                holder.binding.mainLinearLayout.context.resources.getColor(
                    R.color.transparent
                )
            )
            holder.binding.itemName.setTextColor(
                holder.binding.itemName.context.resources.getColor(
                    R.color.kcCaptionLightGray
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return drawerMenu.size
    }

    /**
    Sets the click listener for the RecyclerView item.
    @param onItemClick The click listener to be set.
     */
    fun setOnItemClick(onItemClick: RecyclerviewInterface) {
        setOnItemClick = onItemClick
    }

    class DrawerMenuViewHolder(val binding: DrawerMenuItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
