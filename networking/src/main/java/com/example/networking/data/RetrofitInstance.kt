package com.example.networking.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val endpoint = "http://www.nactem.ac.uk/software/acromine/"

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(endpoint)
        .client(
            OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .build()
        )
        .build()

    val acromineServiceInstance = retrofit.create(AcromineService::class.java)
}