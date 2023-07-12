package com.technource.android.ui.registrationModule

import android.content.Intent
import android.content.res.Resources
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivitySignupBinding
import com.example.android_kotlin_boilerplate.databinding.CountryCodeBotomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.technource.android.base.BaseActivity
import com.technource.android.commonInterface.RecyclerviewInterface
import com.technource.android.databse.AppDatabase
import com.technource.android.databse.RegistrationTable
import com.technource.android.ui.countryCodeMdule.Country
import com.technource.android.ui.countryCodeMdule.CountryAdapter
import com.technource.android.ui.loginModule.LoginActivity
import com.technource.android.ui.viewTermsModule.TermsViewActivity
import com.technource.android.utils.*
import org.json.JSONObject

class SignupActivity : BaseActivity<ActivitySignupBinding>(), SignupNavigator {
    override fun getViewBinding() = ActivitySignupBinding.inflate(layoutInflater)
    private lateinit var viewModel: SignupViewModel
    private lateinit var countryCodeAdapter: CountryAdapter
    private val countryList = ArrayList<Country>()
    lateinit var appDatabase: AppDatabase

    override fun initObj() {
        // Initialize SignupViewModel instance
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]

        //Initialize Database
        appDatabase = AppDatabase.getInstance(this)!!

        // Set viewModel and lifecycle owner for data binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.setNavigator(this)

        // Initialize the CountryAdapter with an empty country list
        countryCodeAdapter = CountryAdapter(countryList)

        // Observe the firstname validation status
        viewModel.firstNameValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.firstNameTextInput.error = errorMessage
            binding.firstNameTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the lastname validation status
        viewModel.lastNameValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.lastnameTextInput.error = errorMessage
            binding.lastnameTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the email validation status
        viewModel.emailValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.emailTextInput.error = errorMessage
            binding.emailTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the username validation status
        viewModel.usernameValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.usernameTextInput.error = errorMessage
            binding.usernameTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the mobile number validation status
        viewModel.mobileNoValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.mobileNoTextInput.error = errorMessage
            binding.mobileNoTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the new password validation status
        viewModel.newPasswordValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.newPasswordTextInput.error = errorMessage
            binding.newPasswordTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the email validation status
        viewModel.confirmPassValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.confirmPasswordTextInput.error = errorMessage
            binding.confirmPasswordTextInput.isErrorEnabled = errorMessage != null
        }

        // Add text change listeners for the input fields
        binding.firstNameET.addTextChangedListener {
            val firstname = it.toString().trim()
            val firstnameStatus = validFirstName(firstname)
            viewModel.firstNameValidationStatus.value = firstnameStatus
        }

        binding.lastnameET.addTextChangedListener {
            val lastname = it.toString().trim()
            val lastnameStatus = validLastName(lastname)
            viewModel.lastNameValidationStatus.value = lastnameStatus
        }

        binding.emailEt.addTextChangedListener {
            val email = it.toString().trim()
            val emailStatus = validateEmail(email)
            viewModel.emailValidationStatus.value = emailStatus
        }

        binding.usernameET.addTextChangedListener {
            val username = it.toString().trim()
            val usernameStatus = validUsername(username)
            viewModel.usernameValidationStatus.value = usernameStatus
        }

        binding.mobileNo.addTextChangedListener {
            val mobileNo = it.toString().trim()
            val mobileNoStatus = validMobile(mobileNo)
            viewModel.mobileNoValidationStatus.value = mobileNoStatus
        }

        binding.newPasswordET.addTextChangedListener {
            val password = it.toString().trim()
            val passwordStatus = validatePassword(password)
            viewModel.newPasswordValidationStatus.value = passwordStatus
        }

        binding.confirmPasswordET.addTextChangedListener {
            val confirmPassword = it.toString().trim()
            val confirmPasswordStatus =
                validConfirmPassword(binding.newPasswordET.text.toString(), confirmPassword)
            viewModel.confirmPassValidationStatus.value = confirmPasswordStatus
        }
    }

    override fun click() {
        binding.backBtn.back.setOnClickListener { finish() }
        binding.loginTV.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finishAffinity()
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }

        // Set click listener for the country code button
        binding.countryCodeBtn.setOnClickListener {
            countryList.clear()
            countryCodeAdapter.notifyDataSetChanged()
            showBottomSheet()
        }

        binding.termsCondition.setOnClickListener {
            val intent = Intent(this@SignupActivity, TermsViewActivity::class.java)
            intent.putExtra(Constants.INTENT_KEY_URL, Constants.TERMS_CONDITION_LINK)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }

        binding.privacyPolicy.setOnClickListener {
            val intent = Intent(this@SignupActivity, TermsViewActivity::class.java)
            intent.putExtra(Constants.INTENT_KEY_URL, Constants.PRIVACY_POLICY_LINK)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }
    }

    override fun signUp() {
        // Get the input values from the text fields
        val firstname: String = binding.firstNameET.text.toString()
        val lastname: String = binding.lastnameET.text.toString()
        val email: String = binding.emailEt.text.toString()
        val username: String = binding.usernameET.text.toString()
        val mobileNo: String = binding.mobileNo.text.toString()
        val newPassword: String = binding.newPasswordET.text.toString()
        val confirmPassword: String = binding.confirmPasswordET.text.toString()

        // Validate the input values
        val firstnameStatus = validFirstName(firstname)
        val lastnameStatus = validLastName(lastname)
        val emailStatus = validateEmail(email)
        val usernameStatus = validUsername(username)
        val mobileNoStatus = validMobile(mobileNo)
        val newPasswordStatus = validatePassword(newPassword)
        val confirmPasswordStatus = validConfirmPassword(newPassword, confirmPassword)

        // Check if all validations pass and the checkbox is checked
        if (firstnameStatus == ValidationStatus.VALID && lastnameStatus == ValidationStatus.VALID &&
            emailStatus == ValidationStatus.VALID && usernameStatus == ValidationStatus.VALID &&
            mobileNoStatus == ValidationStatus.VALID && newPasswordStatus == ValidationStatus.VALID &&
            confirmPasswordStatus == ValidationStatus.VALID && binding.checkbox.isChecked
        ) {
            // Check if the email already exists in the database
            if (appDatabase.registrationDao()?.isEmailExists(email)!!) {
                // Email already exists, show error message
                val duplicateEmailStatus = ValidationStatus.DUPLICATE_EMAIL
                val errorMessage = duplicateEmailStatus.getErrorMessage(this)
                binding.emailTextInput.error = errorMessage
                binding.emailTextInput.isErrorEnabled = errorMessage != null
            } else {
                // Email is unique, proceed with registration
                // Insert new user data into the registration table
                appDatabase.registrationDao()?.insertData(
                    RegistrationTable(
                        firstname,
                        lastname,
                        email,
                        username,
                        binding.countryCodeBtn.text.toString(),
                        mobileNo,
                        newPassword
                    )
                )
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                finish()
                overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
            }
        }

        // Display error messages for invalid fields

        if (!binding.checkbox.isChecked) {
            Toast(this).errorToast(
                resources.getText(R.string.accept_privacy_policy_error_text).toString(), this
            )
        }
        if (firstnameStatus != ValidationStatus.VALID) {
            val errorMessage = firstnameStatus.getErrorMessage(this)
            binding.firstNameTextInput.error = errorMessage
            binding.firstNameTextInput.isErrorEnabled = errorMessage != null
        }

        if (lastnameStatus != ValidationStatus.VALID) {
            val errorMessage = lastnameStatus.getErrorMessage(this)
            binding.lastnameTextInput.error = errorMessage
            binding.lastnameTextInput.isErrorEnabled = errorMessage != null
        }
        if (usernameStatus != ValidationStatus.VALID) {
            val errorMessage = usernameStatus.getErrorMessage(this)
            binding.usernameTextInput.error = errorMessage
            binding.usernameTextInput.isErrorEnabled = errorMessage != null
        }
        if (mobileNoStatus != ValidationStatus.VALID) {
            val errorMessage = mobileNoStatus.getErrorMessage(this)
            binding.mobileNoTextInput.error = errorMessage
            binding.mobileNoTextInput.isErrorEnabled = errorMessage != null
        }
        if (emailStatus != ValidationStatus.VALID) {
            val errorMessage = emailStatus.getErrorMessage(this)
            binding.emailTextInput.error = errorMessage
            binding.emailTextInput.isErrorEnabled = errorMessage != null
        }
        if (newPasswordStatus != ValidationStatus.VALID) {
            val errorMessage = newPasswordStatus.getErrorMessage(this)
            binding.newPasswordTextInput.error = errorMessage
            binding.newPasswordTextInput.isErrorEnabled = errorMessage != null
        }

        if (confirmPasswordStatus != ValidationStatus.VALID) {
            val errorMessage = confirmPasswordStatus.getErrorMessage(this)
            binding.confirmPasswordTextInput.error = errorMessage
            binding.confirmPasswordTextInput.isErrorEnabled = errorMessage != null
        }
    }

    private fun showBottomSheet() {
        // Inflate the bottom sheet layout using data binding
        val bottomSheetBinding = CountryCodeBotomsheetBinding.inflate(layoutInflater)
        // Create a bottom sheet dialog
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
        // Set the content view of the dialog to the inflated layout
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        // Customize the behavior of the bottom sheet dialog
        bottomSheetDialog.behavior.skipCollapsed = true
        bottomSheetDialog.behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED

        // Set up the RecyclerView for displaying country codes
        bottomSheetBinding.rvCountryList.setHasFixedSize(true)
        bottomSheetBinding.rvCountryList.layoutManager = LinearLayoutManager(this)
        bottomSheetBinding.rvCountryList.adapter = countryCodeAdapter

        // Load data from JSON file and populate the country list
        addJSONDataToList()

        // Set up the search functionality
        bottomSheetBinding.searchCountryEt.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    // Filter the country list based on the search query
                    filterList(newText)
                }
                return true
            }
        })

        // Set item click listener for selecting a country
        countryCodeAdapter.setOnItemClick(object : RecyclerviewInterface {
            override fun onItemClick(position: Int) {
                binding.countryCodeBtn.text = "+" + countryList[position].phoneCode
                bottomSheetDialog.dismiss()
            }
        })

        // Show the bottom sheet dialog
        bottomSheetDialog.show()
    }

    /**
    Filters the country list based on the provided query.
    @param query The search query to filter the country list.
     */
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Country>()
            for (i in countryList) {
                if (i.countryName.lowercase().contains(query)) {
                    filteredList.add(i)
                } else if (i.countryCode.lowercase().contains(query)) {
                    filteredList.add(i)
                } else if (i.phoneCode.lowercase().contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                countryCodeAdapter.setFilteredList(filteredList)
            }
        }
    }

    /**
    Adds JSON data to the country list.
    Loads JSON data from the assets folder, parses it, and populates the countryList.
     */
    private fun addJSONDataToList() {
        try {
            // Load the JSON data from the assets folder and create a JSONObject
            val obj = loadJSONFromAssets()?.let { JSONObject(it) }

            // Get the "country" array from the JSONObject
            val contactArray = obj?.getJSONArray("country")

            // Iterate over the country array
            if (contactArray != null) {
                for (i in 0 until contactArray.length()) {
                    val country = contactArray.getJSONObject(i)
                    val name = country.getString("name")
                    val countryCode = country.getString("code")
                    val countryPhoneCode = country.getString("phone_code")
                    val countryArray = Country(name, countryCode, countryPhoneCode)

                    // Create a Country object with the extracted data and add it to the countryList
                    countryList.add(countryArray)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
    Loads JSON data from the "country_list.json" file in the assets folder.
    @return The loaded JSON data as a String, or null if an exception occurs.
     */
    private fun loadJSONFromAssets(): String? {
        val json: String = try {
            // Open and read the "country_list.json" file from the assets folder
            val `is` = applicationContext.assets.open("country_list.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            // Convert the byte array to a String
            String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        // Return the loaded JSON data as a String
        return json
    }

    override fun handleError(throwable: Throwable?) {}
}
