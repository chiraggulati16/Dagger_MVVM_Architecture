package com.example.dagger_mvvm_architecture.data.network.responseUtil


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val message: String?, val data: T?, val error: AppError?) {
    companion object {
        fun <T> success(data: T? = null, message: String): Resource<T> {
            return Resource(Status.SUCCESS, message, data, null)
        }

        fun <T> error(error: AppError, message: String?): Resource<T> {
            return Resource(Status.ERROR, message ,null, error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null,null, null)
        }
    }
}