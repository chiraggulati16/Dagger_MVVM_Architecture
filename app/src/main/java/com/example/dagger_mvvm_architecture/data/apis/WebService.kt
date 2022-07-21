package com.example.dagger_mvvm_architecture.data.apis

import com.example.dagger_mvvm_architecture.data.models.response.UserData
import com.example.dagger_mvvm_architecture.data.network.responseUtil.ApiResponse
import retrofit2.Call
import retrofit2.http.*
import kotlin.collections.HashMap

/**
 * Created by Chirag.
 */
interface WebService {
    companion object {
        const val ENDPOINT = "API Endpoint url"

    }

    /*POST APIS*/
    @FormUrlEncoded
    @POST(ENDPOINT)
    fun userLogin(@FieldMap hashMap: HashMap<String, Any>): Call<ApiResponse<UserData>>

}