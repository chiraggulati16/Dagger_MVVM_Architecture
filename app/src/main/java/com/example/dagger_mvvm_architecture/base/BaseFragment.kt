package com.example.dagger_mvvm_architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V : ViewModel, binding : ViewDataBinding> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (rootView == null) {
            val binding: binding =
                DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
            val viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())

            onCreateView(savedInstanceState, viewModel, binding)
            rootView = binding.root
            rootView
        } else {
            rootView
        }
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    abstract fun getLayoutResId(): Int
    abstract fun getViewModel(): Class<V>
    abstract fun onCreateView(instance: Bundle?, viewModel: V, binding: binding)

}