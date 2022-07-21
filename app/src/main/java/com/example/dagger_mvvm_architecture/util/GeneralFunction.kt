package com.example.dagger_mvvm_architecture.util

import android.app.Activity
import android.view.View
import com.example.dagger_mvvm_architecture.util.PrefsManager.Companion.PREF_PROFILE

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun logoutUser(activity: Activity?, prefsManager: PrefsManager) {

    prefsManager.remove(PREF_PROFILE)
    //create intent to navigate back to login screen

}



