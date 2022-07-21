package com.example.dagger_mvvm_architecture

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<V : ViewModel?,
        B : ViewDataBinding> :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: B = DataBindingUtil.setContentView(this, getLayoutResId())
        val viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())
        onCreate(savedInstanceState, viewModel, binding)
    }

    protected abstract fun getLayoutResId(): Int
    protected abstract fun getViewModel(): Class<V>
    protected abstract fun onCreate(instance: Bundle?, viewModel: V, binding: B)

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount < 1) {
            super.onBackPressed()
        } else {
            val fragmentList = supportFragmentManager.fragments

            var handled = false
            for (f in fragmentList) {
                if (f is BaseFragment<*, *>) {

                    handled = f.onBackPressed()
                    if (handled) {
                        break
                    }
                }
            }

            if (!handled) {
                supportFragmentManager.popBackStack()
            }

            }

    }
}
