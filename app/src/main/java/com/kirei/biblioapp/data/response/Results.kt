package com.kirei.biblioapp.data.response

sealed class Results<out T : Any> {

    data class Success<out T : Any>(val data: T) : Results<T>()
    data class Error(val exception: Throwable) : Results<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }

}