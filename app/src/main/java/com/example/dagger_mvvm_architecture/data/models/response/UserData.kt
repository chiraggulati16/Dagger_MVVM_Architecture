package com.example.dagger_mvvm_architecture.data.models.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserData(
    @SerializedName("firstName") var firstName : String? = null,
    @SerializedName("lastName") var lastName : String? = null,
    @SerializedName("isPublic") var isPublic: Int? = null,
    @SerializedName("isBlocked") var isBlocked: Int? = null,
    @SerializedName("gender") var gender: Int? = null,
) : Serializable