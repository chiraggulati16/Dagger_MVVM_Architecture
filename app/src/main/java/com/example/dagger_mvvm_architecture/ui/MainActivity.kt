package com.example.dagger_mvvm_architecture.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.dagger_mvvm_architecture.BaseActivity
import com.example.dagger_mvvm_architecture.R
import com.example.dagger_mvvm_architecture.data.network.responseUtil.ApisRespHandler
import com.example.dagger_mvvm_architecture.data.network.responseUtil.Status
import com.example.dagger_mvvm_architecture.databinding.ActivityMainBinding
import com.example.dagger_mvvm_architecture.util.PrefsManager
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var prefsManager: PrefsManager
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(
        instance: Bundle?,
        viewModel: MainViewModel,
        binding: ActivityMainBinding
    ) {
        this.binding = binding
        this.viewModel = viewModel

        /*
        use this line only if you added below code in your fragment or activity
        <data>
        <variable
        name="viewModel"
        type="com.example.dagger_mvvm_architecture.ui.MainViewModel" />
        </data>
        */
        this.binding.viewModel = viewModel

        hitApi()
        bindObservers()
    }

    private fun bindObservers() {

        //here you get response in api that you hit in view model
        //loading is used to represent progress bar
        //success is used to get data from api
        viewModel.loginResponse.observe(this, Observer {
            it ?: return@Observer
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {

                }
                Status.ERROR -> {
                    ApisRespHandler.handleError(
                        it.error,
                        this,
                        prefsManager = prefsManager,
                        binding = binding
                    )
                }
            }
        })
    }

    private fun hitApi() {
        //this is how you call api from viewModel class
       // viewModel.login()
    }
}