package com.technource.android.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {
    private var mNavigator: WeakReference<N>? = null

    open fun getNavigator(): N? {
        return mNavigator!!.get()
    }

    open fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }
}