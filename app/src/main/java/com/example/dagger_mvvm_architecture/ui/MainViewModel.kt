package com.example.dagger_mvvm_architecture.ui

import androidx.lifecycle.ViewModel
import com.example.dagger_mvvm_architecture.data.apis.WebService
import com.example.dagger_mvvm_architecture.data.models.response.UserData
import com.example.dagger_mvvm_architecture.data.network.responseUtil.ApiResponse
import com.example.dagger_mvvm_architecture.data.network.responseUtil.ApiUtils
import com.example.dagger_mvvm_architecture.data.network.responseUtil.AppError
import com.example.dagger_mvvm_architecture.data.network.responseUtil.Resource
import com.example.dagger_mvvm_architecture.di.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val webService: WebService
) : ViewModel() {
    val loginResponse by lazy { SingleLiveEvent<Resource<UserData>>() }

    //call this method in main activity
    fun login() {
        val requestHashMap = HashMap<String, Any>()

        //set data to send in api in requestHashMap

        loginResponse.value = Resource.loading()

        webService.userLogin(requestHashMap).enqueue(object : Callback<ApiResponse<UserData>> {
            override fun onFailure(call: Call<ApiResponse<UserData>>, t: Throwable) {
                loginResponse.value = Resource.error(ApiUtils.failure(t), null)
            }

            override fun onResponse(
                call: Call<ApiResponse<UserData>>,
                response: Response<ApiResponse<UserData>>
            ) {
                val statusCode = response.body()?.status
                val message = response.body()?.message

                if (statusCode == 1)
                    loginResponse.value =
                        response.body()?.data?.let { Resource.success(it, message ?: "") }
                else
                    loginResponse.value = Resource.error(
                        AppError.ApiFailure(message ?: ""), null
                    )
            }
        })
    }

}