package com.technource.android.ui.editProfileModule

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.ActivityEditProfileBinding
import com.example.android_kotlin_boilerplate.databinding.CountryCodeBotomsheetBinding
import com.example.android_kotlin_boilerplate.databinding.EditProfileBottomSheetBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.technource.android.base.BaseActivity
import com.technource.android.commonInterface.RecyclerviewInterface
import com.technource.android.ui.countryCodeMdule.Country
import com.technource.android.ui.countryCodeMdule.CountryAdapter
import com.technource.android.utils.*
import org.json.JSONObject

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>(), EditProfileNavigator {
    override fun getViewBinding() = ActivityEditProfileBinding.inflate(layoutInflater)
    private lateinit var viewModel: EditProfileViewModel
    private lateinit var countryCodeAdapter: CountryAdapter
    private val countryList = ArrayList<Country>()
    private val WRITE_STORAGE_PERMISSION_CODE = 1

    override fun initObj() {
        // Initialize the ViewModel and set up the data binding
        viewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.setNavigator(this)

        countryCodeAdapter = CountryAdapter(countryList)

        // Set up the app bar title
        binding.appbar.appBarText.text = resources.getString(R.string.edit_profile)

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

        // Observe the home address validation status
        viewModel.homeAddressValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.homeAddressTextInput.error = errorMessage
            binding.homeAddressTextInput.isErrorEnabled = errorMessage != null
        }

        // Observe the office address validation status
        viewModel.officeAddressValidationStatus.observe(this) { status ->
            val errorMessage = status.getErrorMessage(this)
            binding.officeAddressTextInput.error = errorMessage
            binding.officeAddressTextInput.isErrorEnabled = errorMessage != null
        }


        // Set up text change listeners for various input fields
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

        binding.homeAddressET.addTextChangedListener {
            val homeAddress = it.toString().trim()
            val homeAddressStatus = validHomeAddress(homeAddress)
            viewModel.homeAddressValidationStatus.value = homeAddressStatus
        }

        binding.officeAddressET.addTextChangedListener {
            val officeAddress = it.toString().trim()
            val officeAddressStatus = validOfficeAddress(officeAddress)
            viewModel.officeAddressValidationStatus.value = officeAddressStatus
        }
    }

    override fun click() {
        binding.appbar.back.setOnClickListener {
            finish()
        }

        binding.countryCodeBtn.setOnClickListener {
            countryList.clear()
            countryCodeAdapter.notifyDataSetChanged()
            showBottomSheet()
        }

        binding.avatarEdit.setOnClickListener { checkPermission() }
    }

    override fun save() {
        // Retrieve values from input fields
        val firstname: String = binding.firstNameET.text.toString()
        val lastname: String = binding.lastnameET.text.toString()
        val email: String = binding.emailEt.text.toString()
        val username: String = binding.usernameET.text.toString()
        val mobileNo: String = binding.mobileNo.text.toString()
        val homeAddress: String = binding.homeAddressET.text.toString()
        val officeAddress: String = binding.officeAddressET.text.toString()

        // Perform validation for each field and retrieve their status
        val firstnameStatus = validFirstName(firstname)
        val lastnameStatus = validLastName(lastname)
        val emailStatus = validateEmail(email)
        val usernameStatus = validUsername(username)
        val mobileNoStatus = validMobile(mobileNo)
        val homeAddressStatus = validMobile(homeAddress)
        val officeAddressStatus = validMobile(officeAddress)

        // Check if all fields are valid
        if (firstnameStatus == ValidationStatus.VALID && lastnameStatus == ValidationStatus.VALID &&
            emailStatus == ValidationStatus.VALID && usernameStatus == ValidationStatus.VALID &&
            mobileNoStatus == ValidationStatus.VALID && homeAddressStatus == ValidationStatus.VALID &&
            officeAddressStatus == ValidationStatus.VALID
        ) {
            Toast(this).successToast(resources.getString(R.string.profile_update), this)
            finish()
        }

        // If any field is not valid, display respective error messages
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

        if (homeAddressStatus != ValidationStatus.VALID) {
            val errorMessage = homeAddressStatus.getErrorMessage(this)
            binding.homeAddressTextInput.error = errorMessage
            binding.homeAddressTextInput.isErrorEnabled = errorMessage != null
        }

        if (officeAddressStatus != ValidationStatus.VALID) {
            val errorMessage = officeAddressStatus.getErrorMessage(this)
            binding.officeAddressTextInput.error = errorMessage
            binding.officeAddressTextInput.isErrorEnabled = errorMessage != null
        }
    }

    override fun handleError(throwable: Throwable?) {

    }

    /**
    Shows the bottom sheet dialog with the country list.
    The bottom sheet contains a RecyclerView populated with country data.
    The user can search for countries and select a country from the list.
    When a country is selected, the country code is updated in the main activity and the bottom sheet is dismissed.
     */
    private fun showBottomSheet() {
        // Inflate the layout for the bottom sheet
        val bottomSheetBinding = CountryCodeBotomsheetBinding.inflate(layoutInflater)

        // Create a BottomSheetDialog with a custom style
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        // Configure the behavior of the bottom sheet dialog
        bottomSheetDialog.behavior.skipCollapsed = true
        bottomSheetDialog.behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED

        // Set up the RecyclerView for the country list
        bottomSheetBinding.rvCountryList.setHasFixedSize(true)
        bottomSheetBinding.rvCountryList.layoutManager = LinearLayoutManager(this)
        bottomSheetBinding.rvCountryList.adapter = countryCodeAdapter

        // Add JSON data to the country list
        addJSONDataToList()

        // Set up the search functionality for the country list
        bottomSheetBinding.searchCountryEt.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filterList(newText)
                }
                return true
            }
        })

        countryCodeAdapter.setOnItemClick(object : RecyclerviewInterface {
            override fun onItemClick(position: Int) {
                binding.countryCodeTv.text = "+" + countryList[position].phoneCode
                binding.countryCodeTv.setTextColor(resources.getColor(R.color.hintColor))
                bottomSheetDialog.dismiss()
            }
        })
        bottomSheetDialog.show()
    }

    /**
    Filters the country list based on the given query.
    The query is used to search for matching country names, country codes, and phone codes.
    The filtered list is updated in the country code adapter.
    If the filtered list is empty, a toast message is displayed indicating no data found.
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
    Adds JSON data to the country list by parsing the "country_list.json" file from the assets folder.
    The JSON data is extracted to create Country objects containing the country name, country code, and phone code.
    The Country objects are added to the countryList.
    If an exception occurs during the process, it is printed to the console.
     */

    private fun addJSONDataToList() {
        try {
            val obj = loadJSONFromAssets()?.let { JSONObject(it) }
            val contactArray = obj?.getJSONArray("country")
            if (contactArray != null) {
                for (i in 0 until contactArray.length()) {
                    val country = contactArray.getJSONObject(i)
                    val name = country.getString("name")
                    val countryCode = country.getString("code")
                    val countryPhoneCode = country.getString("phone_code")
                    val countryArray = Country(name, countryCode, countryPhoneCode)
                    countryList.add(countryArray)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
    Loads and reads the "country_list.json" file from the assets folder.
    The contents of the file are converted into a String and returned.
    If an exception occurs during the process, it is printed to the console and null is returned.
    @return The loaded JSON data as a String, or null if an exception occurs.
     */
    private fun loadJSONFromAssets(): String? {
        val json: String = try {
            val `is` = applicationContext.assets.open("country_list.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }

    /**
    Opens the edit profile bottom sheet.
    The layout for the bottom sheet is inflated and displayed as a BottomSheetDialog.
    Click listeners are set for the camera and gallery buttons, which perform specific actions and dismiss the bottom sheet.
     */
    private fun openEditProfileBottomSheet() {
        // Inflate the layout for the edit profile bottom sheet
        val bottomSheetBinding = EditProfileBottomSheetBinding.inflate(layoutInflater)

        // Create a BottomSheetDialog with a custom style
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)

        // Set click listener for the camera button
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.camera.setOnClickListener {
            openCamera()
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.gallery.setOnClickListener {
            openGallery()
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    /**
    Opens the device's gallery using an image picker library.
    It configures the image picker with the following options:
    galleryOnly: Only allows selecting images from the gallery.
    crop: Enables cropping of the selected image.
    compress: Specifies the maximum size of the compressed image to be less than 1 MB.
    maxResultSize: Sets the maximum resolution of the selected image to be less than 1080 x 1080.
    It creates an intent with the configured options and launches it using the cameraRequest activity result launcher.
     */
    private fun openGallery() {
        ImagePicker.with(this)
            .galleryOnly()
            .crop()
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent -> cameraRequest.launch(intent) }
    }

    /**
    Opens the device's camera using an image picker library.
    It configures the image picker with the following options:
    cameraOnly: Only allows capturing images using the camera.
    crop: Enables cropping of the captured image.
    compress: Specifies the maximum size of the compressed image to be less than 1 MB.
    maxResultSize: Sets the maximum resolution of the captured image to be less than 1080 x 1080.
    It creates an intent with the configured options and launches it using the cameraRequest activity result launcher.
     */
    private fun openCamera() {
        ImagePicker.with(this)
            .cameraOnly()
            .crop()
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent -> cameraRequest.launch(intent) }
    }

    // Check and request necessary permissions
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            ) {
                val permission =
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, WRITE_STORAGE_PERMISSION_CODE)
            }
        }
    }

    // Handle the result of the camera activity
    private val cameraRequest = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data

        when (resultCode) {
            Activity.RESULT_OK -> {
                val fileUri = data?.data!!
                binding.userPhoto.setImageURI(fileUri)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    // Handle the result of permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WRITE_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openEditProfileBottomSheet()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}