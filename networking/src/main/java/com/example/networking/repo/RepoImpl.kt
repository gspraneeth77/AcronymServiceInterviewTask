package com.example.networking.repo

import android.util.Log
import com.example.networking.data.AcromineService
import com.example.networking.domain.Repo
import com.willor.lib_data.domain.dataobjects.AcromineResp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RepoImpl(
    private val acromineApi: AcromineService
) : Repo {

    private val tag = RepoImpl::class.java.simpleName

    /**
     * Search for acronym in it's abbreviated format
     */
    override suspend fun searchByAcronymShortForm(acronymShortForm: String): Flow<AcromineResp?> =
        flow {

            val resp = acromineApi.getAcronymSearch(acronymShortForm.uppercase(), "")

            if (resp.isSuccessful && !resp.body().isNullOrEmpty()) {
                emit(resp.body())
            } else {
                Log.d(
                    tag, "searchByAcronymLongForm called with argument: $acronymShortForm" +
                            " FAILED. Response Code: ${resp.code()}. Returning Null"
                )
                emit(null)
            }
        }

    /**
     * Search for acronym in it's long form word format
     */
    override suspend fun searchByAcronymLongForm(acronymLongForm: String): Flow<AcromineResp?> =
        flow {

            val resp = acromineApi.getAcronymSearch("", acronymLongForm)

            if (resp.isSuccessful && !resp.body().isNullOrEmpty()) {
                emit(resp.body())
            } else {
                Log.d(
                    tag, "searchByAcronymLongForm called with argument: $acronymLongForm" +
                            " FAILED. Response Code: ${resp.code()}. Returnign Null"
                )
                emit(null)
            }
        }


}