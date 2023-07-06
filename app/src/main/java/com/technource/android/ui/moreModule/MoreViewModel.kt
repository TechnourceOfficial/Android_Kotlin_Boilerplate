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
            MoreModel("About us", Constants.aboutUs),
            MoreModel("Terms & Condition", Constants.termsNCondition),
            MoreModel("Privacy Policy", Constants.privacyPolicy)
        )
        more.value = moreList
    }

}