package com.technource.android.ui.countryCodeMdule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin_boilerplate.databinding.CountryCodeItemLayoutBinding
import com.technource.android.commonInterface.RecyclerviewInterface

class CountryAdapter(private var countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private lateinit var setOnItemClick: RecyclerviewInterface

    fun setOnItemClick(onItemClick: RecyclerviewInterface) {
        setOnItemClick = onItemClick
    }

    fun setFilteredList(mList: List<Country>) {
        this.countryList = mList
        notifyDataSetChanged()
    }

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

    class CountryViewHolder(val binding: CountryCodeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
