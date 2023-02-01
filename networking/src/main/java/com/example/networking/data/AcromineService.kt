package com.example.networking.data

import com.willor.lib_data.domain.dataobjects.AcromineResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineService {

    @GET("dictionary.py")
    suspend fun getAcronymSearch(
        @Query("sf") shortForm: String,
        @Query("lf") longForm: String
    ): Response<AcromineResp?>

}