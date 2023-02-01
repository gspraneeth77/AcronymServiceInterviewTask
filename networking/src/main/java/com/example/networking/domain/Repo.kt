package com.example.networking.domain

import com.willor.lib_data.domain.dataobjects.AcromineResp
import kotlinx.coroutines.flow.Flow

interface Repo {

    suspend fun searchByAcronymShortForm(acronymShortForm: String): Flow<AcromineResp?>

    suspend fun searchByAcronymLongForm(acronymLongForm: String): Flow<AcromineResp?>

}