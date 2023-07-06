package com.technource.android.ui.moreModule

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityAboutBinding
import com.technource.android.base.BaseActivity
import com.technource.android.commonInterface.RecyclerviewInterface
import com.technource.android.ui.viewTermsModule.TermsViewActivity
import com.technource.android.utils.Constants

class AboutActivity : BaseActivity<ActivityAboutBinding>() {
    override fun getViewBinding() = ActivityAboutBinding.inflate(layoutInflater)
    private lateinit var moreListAdapter: MoreListAdapter
    val moreModel = ArrayList<MoreModel>()
    private lateinit var moreViewModel: MoreViewModel
    override fun initObj() {
        // Initialize MoreViewModel instance
        moreViewModel = ViewModelProvider(this)[MoreViewModel::class.java]

        // Set viewModel and lifecycle owner for data binding
        binding.viewModel = moreViewModel
        binding.lifecycleOwner = this

        // Set the app bar text to "More"
        binding.appbar.appBarText.text = resources.getString(R.string.more)

        // Create and set up the MoreListAdapter
        moreListAdapter = MoreListAdapter()
        binding.rvMoreList.layoutManager = LinearLayoutManager(this)
        binding.rvMoreList.adapter = moreListAdapter

        // Observe the list of items from the MoreViewModel
        moreViewModel.getList().observe(this) { moreList ->
            moreListAdapter.setData(moreList)
            moreModel.addAll(moreList)
        }

        // Set item click listener for the MoreListAdapter
        moreListAdapter.setOnItemClick(object : RecyclerviewInterface {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@AboutActivity, TermsViewActivity::class.java)
                intent.putExtra(Constants.INTENT_KEY_URL, moreModel[position].url)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
            }
        })
    }

    override fun click() {
        binding.appbar.back.setOnClickListener {
            finish()
        }
    }
}