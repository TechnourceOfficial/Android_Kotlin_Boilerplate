package com.technource.android.ui.countryCodeMdule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin_boilerplate.databinding.CountryCodeItemLayoutBinding
import com.technource.android.commonInterface.RecyclerviewInterface

class CountryAdapter(private var countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private lateinit var setOnItemClick: RecyclerviewInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view =
            CountryCodeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.tvCountryName.text = countryList[position].countryName
        holder.binding.tvCountryCode.text = "(" + countryList[position].countryCode + ")"
        holder.binding.tvCountryPhoneCode.text = countryList[position].phoneCode
        holder.itemView.setOnClickListener {
            setOnItemClick.onItemClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    /**
    Sets the click listener for the RecyclerView item.
    @param onItemClick The click listener to be set.
     */
    fun setOnItemClick(onItemClick: RecyclerviewInterface) {
        setOnItemClick = onItemClick
    }

    /**
    Sets the filtered list of countries and notifies the adapter of the data change.
    @param mList The filtered list of countries to be set.
     */
    fun setFilteredList(mList: List<Country>) {
        this.countryList = mList
        notifyDataSetChanged()
    }

    class CountryViewHolder(val binding: CountryCodeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
