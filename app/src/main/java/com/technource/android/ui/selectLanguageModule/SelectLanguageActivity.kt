package com.technource.android.ui.selectLanguageModule

import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivitySelectLanguageBinding
import com.technource.android.base.BaseActivity
import com.technource.android.preference.PreferencesHelperImpl
import com.technource.android.ui.introScreenModule.OnBoardActivity
import com.technource.android.utils.changeLanguage
import com.technource.android.utils.errorToast
import java.util.*

class SelectLanguageActivity : BaseActivity<ActivitySelectLanguageBinding>() {
    override fun getViewBinding() = ActivitySelectLanguageBinding.inflate(layoutInflater)
    private lateinit var preference: PreferencesHelperImpl
    private lateinit var viewModel: LanguageViewModel
    private lateinit var selectLanguage: Locale
    private lateinit var adapter: ArrayAdapter<String>

    override fun initObj() {
        preference = PreferencesHelperImpl(this)
        // Initialize LanguageViewModel instance
        viewModel =
            ViewModelProvider(this)[LanguageViewModel::class.java]


        // Get the list of languages from the view model
        val language = viewModel.getLanguages(this)

        // Create an ArrayAdapter with the language list
        adapter = ArrayAdapter(this, R.layout.language_item_layout, language)

        // Set the adapter to the AutoCompleteTextView
        binding.languages.freezesText = false
        binding.languages.setAdapter(adapter)

        // Show dropdown when the AutoCompleteTextView is clicked
        binding.languages.setOnClickListener { binding.languages.showDropDown() }

        // Handle item click in the AutoCompleteTextView dropdown
        binding.languages.setOnItemClickListener { parent, _, position, _ ->
            val selectedLanguage = parent.getItemAtPosition(position).toString()
            viewModel.setSelectedLanguage(selectedLanguage)
            // Perform actions based on the selected language position
            when (position) {
                0 -> {
                    // Check if the selected language is different from the current language
                    if (preference.getLanguageCode() != resources.getString(R.string.english_code)) {
                        selectLanguage = Locale(resources.getString(R.string.english_code))
                        setLanguage(
                            resources.getString(R.string.english_code),
                            resources.getString(R.string.english)
                        )
                    }
                }
                1 -> {
                    if (preference.getLanguageCode() != resources.getString(R.string.french_code)) {
                        selectLanguage = Locale(resources.getString(R.string.french_code))
                        setLanguage(
                            resources.getString(R.string.french_code),
                            resources.getString(R.string.french)
                        )
                    }
                }
                2 -> {
                    if (preference.getLanguageCode() != resources.getString(R.string.russian_code)) {
                        selectLanguage = Locale(resources.getString(R.string.russian_code))
                        setLanguage(
                            resources.getString(R.string.russian_code),
                            resources.getString(R.string.russian)
                        )
                    }
                }
            }
        }
    }

    override fun click() {
        binding.nextBtn.setOnClickListener {
            if (binding.languages.text.isNotEmpty()) {
                startActivity(Intent(this@SelectLanguageActivity, OnBoardActivity::class.java))
                finish()
                overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
                6
            } else {
                Toast(this).errorToast(resources.getString(R.string.please_select_language), this)
            }
        }
    }

    private fun setLanguage(languageCode: String, languageName: String) {
        // Update language preferences
        preference.setLanguage(languageName)
        preference.setLanguageCode(languageCode)

        // Change the language and restart the activity
        changeLanguage(this, selectLanguage)
        restartActivity()
    }

    private fun restartActivity() {
        binding.selectLangTitle.text =
            resources.getString(R.string.select_language_hint)
        binding.languages.hint =
            resources.getString(R.string.select_language_hint)
        binding.nextBtn.text = resources.getString(R.string.intro_next)
    }
}