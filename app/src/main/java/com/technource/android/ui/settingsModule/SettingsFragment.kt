package com.technource.android.ui.settingsModule

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.android_kotlin_boilerplate.R
import com.example.android_kotlin_boilerplate.databinding.FragmentSettingsBinding
import com.example.android_kotlin_boilerplate.databinding.LogoutDialogBinding
import com.technource.android.base.BaseFragment
import com.technource.android.commonInterface.RecyclerviewInterface
import com.technource.android.preference.PreferencesHelperImpl
import com.technource.android.ui.moreModule.AboutActivity
import com.technource.android.ui.changeLanguageModule.ChangeLanguageActivity
import com.technource.android.ui.editProfileModule.EditProfileActivity
import com.technource.android.ui.loginModule.LoginActivity
import com.technource.android.utils.Constants

class SettingsFragment(private val mActivity: AppCompatActivity) :
    BaseFragment<FragmentSettingsBinding>() {
    override fun getViewBinding() = FragmentSettingsBinding.inflate(layoutInflater)
    private lateinit var preference: PreferencesHelperImpl
    private lateinit var drawerAdapter: DrawerMenuItemAdapter
    private val drawerItemList = ArrayList<DrawerMenu>()
    var isChangeLanguageSelected = false

    override fun initObj() {
        preference = PreferencesHelperImpl(mActivity)

        // Open the drawer from the end of the screen
        binding.drawerLayout.openDrawer(GravityCompat.END)

        addDataInMenu()
        drawerAdapter = DrawerMenuItemAdapter(drawerItemList)

        binding.drawerList.layoutManager = LinearLayoutManager(mActivity)
        binding.drawerList.adapter = drawerAdapter
        (binding.drawerList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        drawerAdapter.setOnItemClick(object : RecyclerviewInterface {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {}
                    1 -> {}
                    2 -> {}
                    3 -> {}
                    4 -> {
                        // Start the ChangeLanguageActivity when "Change Language" is selected
                        isChangeLanguageSelected = true
                        startActivity(Intent(mActivity, ChangeLanguageActivity::class.java))
                        mActivity.overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
                    }
                    5 -> {
                        // Open the GitHub repository in a browser when "Git Repo" is selected
                        val uri: Uri =
                            Uri.parse(Constants.GIT_REPO_LINK) // missing 'http://' will cause crashed

                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }
                    6 -> {
                        // Start the MoreActivity when "More" is selected
                        startActivity(Intent(mActivity, AboutActivity::class.java))
                        mActivity.overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
                    }
                    else -> {
                    }
                }
            }

        })
    }

    override fun click() {
        binding.navigationHeader.editProfile.setOnClickListener {
            startActivity(Intent(mActivity, EditProfileActivity::class.java))
            mActivity.overridePendingTransition(R.anim.slide_in_up, R.anim.nothing_ani)
        }

        binding.logoutBtn.setOnClickListener { openLogoutDialog() }
    }


    /**
    Opens the logout dialog.
    The dialog is created using a custom Dialog class and inflated with the logout_dialog.xml layout.
    The layout parameters are set to match the parent view.
    The dialog is set to be non-cancelable to prevent dismissing it by clicking outside.
    Click listeners are set for the "Logout" button, "Cancel" button, and "Close" button.
    When the "Logout" button is clicked, the user preferences are cleared, and the user is redirected to the LoginActivity.
    The finishAffinity() method is called to finish all activities in the current task.
    When the "Cancel" or "Close" button is clicked, the dialog is dismissed.
     */
    private fun openLogoutDialog() {
        // Create and show the logout dialog
        val logoutDialog = Dialog(mActivity)
        val window: Window? = logoutDialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        logoutDialog.setCancelable(false)
        val logoutDialogBinding = LogoutDialogBinding.inflate(layoutInflater)
        logoutDialog.setContentView(logoutDialogBinding.root)

        // Handle click on the "Logout" button
        logoutDialogBinding.logoutBtn.setOnClickListener {
            preference.clear()
            startActivity(Intent(mActivity, LoginActivity::class.java))
            mActivity.finishAffinity()
        }
        logoutDialogBinding.cancelBtn.setOnClickListener { logoutDialog.dismiss() }
        logoutDialogBinding.closeDialogBtn.setOnClickListener { logoutDialog.dismiss() }
        logoutDialog.show()
    }


    /**
    Adds data to the menu list in the drawer.
    The existing drawerItemList is cleared.
    DrawerMenu objects representing the menu items are created and added to the drawerItemList.
    Each menu item consists of a title, subtitle, and icon.
    The titles and icons are retrieved from resources using string resource IDs and drawable resource IDs.
    The subtitle for the "Change Language" menu item is dynamically set based on the language code stored in preferences.
     */
    private fun addDataInMenu() {
        drawerItemList.clear()
        drawerItemList.add(
            DrawerMenu(
                resources.getString(R.string.bottom_menu_1),
                "",
                R.drawable.ic_bullet
            )
        )
        drawerItemList.add(
            DrawerMenu(
                resources.getString(R.string.bottom_menu_2),
                "",
                R.drawable.ic_bullet
            )
        )
        drawerItemList.add(
            DrawerMenu(
                resources.getString(R.string.bottom_menu_3),
                "",
                R.drawable.ic_bullet
            )
        )
        drawerItemList.add(
            DrawerMenu(
                resources.getString(R.string.bottom_menu_4),
                "",
                R.drawable.ic_bullet
            )
        )
        drawerItemList.add(
            DrawerMenu(
                resources.getString(R.string.change_language),
                preference.getLanguageCode().uppercase() + "  〉",
                R.drawable.ic_language
            )
        )
        drawerItemList.add(
            DrawerMenu(
                resources.getString(R.string.git_repo),
                "",
                R.drawable.ic_github
            )
        )
        drawerItemList.add(DrawerMenu(resources.getString(R.string.more), "", R.drawable.ic_more))
    }

    /**
    Refreshes the drawer menu.
    Resets the isChangeLanguageSelected flag to false.
    Adds data to the menu items in the drawer.
    Notifies the drawerAdapter that the data set has changed.
    Updates the text of the logout, deactivate, and delete account TextViews with the corresponding string resources.
     */
    private fun refreshDrawer() {
        isChangeLanguageSelected = false
        addDataInMenu()
        drawerAdapter.notifyDataSetChanged()
        binding.logoutTv.text =
            resources.getString(R.string.logout)
        binding.deactivateTv.text =
            resources.getString(R.string.deactivate_account)
        binding.deleteTV.text =
            resources.getString(R.string.delete_account)
    }

    override fun onResume() {
        super.onResume()
        if (isChangeLanguageSelected)
            refreshDrawer()
    }
}