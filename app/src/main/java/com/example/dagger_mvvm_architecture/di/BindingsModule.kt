package com.example.dagger_mvvm_architecture.di

import com.example.dagger_mvvm_architecture.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingsModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}