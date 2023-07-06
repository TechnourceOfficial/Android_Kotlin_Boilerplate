package com.technource.android.ui.viewTermsModule

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.android_kotlin_boilerplate.databinding.ActivityTermsViewBinding
import com.technource.android.base.BaseActivity
import com.technource.android.utils.Constants

class TermsViewActivity : BaseActivity<ActivityTermsViewBinding>() {
    override fun getViewBinding() = ActivityTermsViewBinding.inflate(layoutInflater)

    override fun initObj() {
        val url: String? = intent.getStringExtra(Constants.INTENT_KEY_URL)
        // Configure WebView settings
        val webViewSettings = binding.webView.settings
        webViewSettings.javaScriptEnabled = true

        // Set WebViewClient to handle events and navigation
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                // Handle page loading started event
                binding.progressBar.visibility = View.VISIBLE
                binding.webView.visibility = View.GONE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                // Handle page loading finished event
                binding.progressBar.visibility = View.GONE
                binding.webView.visibility = View.VISIBLE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                // Handle error event
            }
        }

        // Load a webpage or HTML content
        binding.webView.loadUrl(url.toString())

    }

    override fun click() {
        binding.appbar.back.setOnClickListener { finish() }
    }

}