package com.technource.android.ui.changeLanguageModule

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityChangeLanguageBinding
import com.technource.android.base.BaseActivity
import com.technource.android.preference.PreferencesHelperImpl
import com.technource.android.ui.selectLanguageModule.LanguageViewModel
import com.technource.android.utils.changeLanguage
import java.util.*

class ChangeLanguageActivity : BaseActivity<ActivityChangeLanguageBinding>() {
    override fun getViewBinding() = ActivityChangeLanguageBinding.inflate(layoutInflater)
    private lateinit var preference: PreferencesHelperImpl
    private lateinit var viewModel: LanguageViewModel
    private lateinit var selectLanguage: Locale
    private lateinit var adapter: ArrayAdapter<String>
    override fun initObj() {
        binding.appbar.back.setOnClickListener {
            finish()
        }

        // Set the text for the app bar title
        binding.appbar.appBarText.setText(R.string.change_language)

        preference = PreferencesHelperImpl(this)
        viewModel =
            ViewModelProvider(this)[LanguageViewModel::class.java]
        val language = viewModel.getLanguages(this)

        // Create an ArrayAdapter for the language dropdown
        adapter = ArrayAdapter(this, R.layout.language_item_layout, language)
        binding.languages.freezesText = false
        binding.languages.setAdapter(adapter)

        // Show the dropdown when the language field is clicked
        binding.languages.setOnClickListener { binding.languages.showDropDown() }

        // Handle item selection from the language dropdown
        binding.languages.setOnItemClickListener { parent, _, position, _ ->
            val selectedLanguage = parent.getItemAtPosition(position).toString()
            viewModel.setSelectedLanguage(selectedLanguage)

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

    private fun setLanguage(languageCode: String, languageName: String) {
        // Update language preferences
        preference.setLanguage(languageName)
        preference.setLanguageCode(languageCode)

        // Change the language and restart the activity
        changeLanguage(this, selectLanguage)
        restartActivity()
    }

    private fun restartActivity() {
        binding.appbar.appBarText.text =
            resources.getString(R.string.change_language)
    }

    override fun click() {}
}