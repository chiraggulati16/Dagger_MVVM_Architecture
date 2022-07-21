package com.example.dagger_mvvm_architecture.di

import android.content.Context
import android.content.res.Resources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceProvider @Inject constructor(private val context: Context) {

    fun getStringResource(stringId: Int): String {
        return context.getString(stringId)
    }

    fun getResources(): Resources {
        return context.resources
    }
}