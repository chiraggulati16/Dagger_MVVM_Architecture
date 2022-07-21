package com.example.dagger_mvvm_architecture.di


import com.dellainfotech.di.ViewModelsModule
import com.example.dagger_mvvm_architecture.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BindingsModule::class,
    NetworkModule::class,
    ViewModelsModule::class
])

@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}