package com.dellainfotech.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagger_mvvm_architecture.di.ViewModelKey
import com.example.dagger_mvvm_architecture.di.ViewModelProviderFactory
import com.example.dagger_mvvm_architecture.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelsModule {

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun viewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory =
            factory
    }

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

}