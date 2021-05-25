package com.kirei.biblioapp.data.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiCreator {

    fun <T> get(type: Class<T>): T {

        val okBuilder = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.mashape.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okBuilder.build())
            .build()
            .create(type)
    }


}