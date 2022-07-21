package com.example.dagger_mvvm_architecture.data.network.responseUtil

data class ApiResponse<out T>(
        val status : Int? = null,
        val message: String? = null,
        val error: String? = null,
        val data: T? = null
)