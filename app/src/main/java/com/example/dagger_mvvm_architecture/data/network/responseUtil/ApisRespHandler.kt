package com.example.dagger_mvvm_architecture.data.network.responseUtil

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import com.example.dagger_mvvm_architecture.util.PrefsManager
import com.example.dagger_mvvm_architecture.util.logoutUser

object ApisRespHandler {

    private var alertDialog: AlertDialog.Builder? = null

    fun handleError(
        error: AppError?,
        activity: Activity,
        prefsManager: PrefsManager,
        binding: ViewDataBinding
    ) {
        error ?: return

        when (error) {
            is AppError.ApiError -> {
                if (alertDialog == null)
                    errorMessage(activity, error.message, binding.root, prefsManager)
            }

            is AppError.ApiUnauthorized -> {
                if (alertDialog == null)
                    sessionExpired(activity, error.message, prefsManager)
            }

            is AppError.ApiAccountBlock -> {
//                if (alertDialog == null)
//                    accountDeleted(activity, error.message, prefsManager)
            }

            is AppError.ApiAccountRuleChanged -> {
//                if (alertDialog == null)
//                    accountDeleted(activity, error.message, prefsManager)
            }

            is AppError.ApiFailure -> {
                if (alertDialog == null) {
                    if (error.message == "Unable to authenticate user")
                        sessionExpired(activity, error.message, prefsManager)
                    else {
                        if (error.message == "Your account has been disabled.") {
                            errorMessage(
                                activity,
                                "Your account has been disabled. Please contact us for any questions regarding your account at PlacezApp@gmail.com",
                                binding.root,
                                prefsManager
                            )
                        } else {
                            errorMessage(activity, error.message, binding.root, prefsManager)
                        }
                    }
                }
            }
        }
    }


    private fun sessionExpired(activity: Activity, message: String?, prefsManager: PrefsManager) {
        try {
            alertDialog = AlertDialog.Builder(activity)
            alertDialog?.setCancelable(false)
           // alertDialog?.setTitle(activity.getString(R.string.account_use))
            alertDialog?.setMessage("User can only be logged in on one device.")
            alertDialog?.setPositiveButton("Login") { _, _ ->
                logoutUser(activity, prefsManager)
                alertDialog = null
            }
            alertDialog?.show()
        } catch (ignored: Exception) {
        }
    }


    private fun errorMessage(
        activity: Activity,
        message: String?,
        root: View,
        prefsManager: PrefsManager
    ) {
       // do code for error
    }
}