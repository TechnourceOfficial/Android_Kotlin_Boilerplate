package com.technource.android.ui.moreModule

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_kotlin_boilerplate.R
import com.technource.android.utils.Constants

class MoreViewModel(private val resources: Resources) : ViewModel() {
    private val more: MutableLiveData<List<MoreModel>> = MutableLiveData()
    init {
        // Fetch data from a data source and update the moreList LiveData
        fetchData()
    }

    /**
    Returns the LiveData object containing the list of MoreModel objects.
    The LiveData object is used to observe changes to the list in the UI.
    @return The LiveData object representing the list of MoreModel objects.
     */
    fun getList(): LiveData<List<MoreModel>> {
        return more
    }

    /**
    Fetches the data asynchronously and updates the users LiveData.
    The data is fetched from a data source or generated locally.
    In this example, a list of MoreModel objects is created and assigned to the more LiveData.
    The observers of the LiveData will be notified of the updated data.
     */
    private fun fetchData() {
        val moreList = listOf(
            MoreModel(resources.getString(R.string.about_us), Constants.ABOUT_US_LINK),
            MoreModel(resources.getString(R.string.terms_and_conditions), Constants.TERMS_CONDITION_LINK),
            MoreModel(resources.getString(R.string.privacy_policy), Constants.PRIVACY_POLICY_LINK)
        )
        more.value = moreList
    }

}