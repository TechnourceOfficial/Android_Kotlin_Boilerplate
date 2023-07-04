package com.technource.android.ui.moreModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technource.android.utils.Constants

class MoreViewModel : ViewModel() {
    private val more: MutableLiveData<List<MoreModel>> = MutableLiveData()

    init {
        // Fetch data from a data source and update the moreList LiveData
        fetchData()
    }

    fun getList(): LiveData<List<MoreModel>> {
        return more
    }

    private fun fetchData() {
        // Perform an asynchronous operation to fetch the data
        // Once the data is available, update the users LiveData
        // For example:
        val moreList = listOf(
            MoreModel("About us", Constants.aboutUs),
            MoreModel("Terms & Condition", Constants.termsNCondition),
            MoreModel("Privacy Policy", Constants.privacyPolicy)
        )
        more.value = moreList
    }

}