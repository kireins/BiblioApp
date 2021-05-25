package com.kirei.biblioapp.data.services



import com.kirei.biblioapp.BuildConfig
import com.kirei.biblioapp.data.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface Api {

    @Headers(
        "x-rapidapi-key:${BuildConfig.API_KEY}",
        "Content-type:application/json"
    )

    @GET("/define")
    fun getDefinitionAsync(@Query("term") word: String): Deferred<BaseResponse>




}