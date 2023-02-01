package com.example.acronymserviceinterviewtask.mainactivityviewmodel_test

import com.example.networking.domain.Repo
import com.willor.lib_data.domain.dataobjects.AcromineResp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepo : Repo {


    override suspend fun searchByAcronymShortForm(acronymShortForm: String): Flow<AcromineResp?> =
        flow {
            if (acronymShortForm.isEmpty()) {
                emit(null)
            } else {
                emit(dummyData)
            }
        }

    override suspend fun searchByAcronymLongForm(acronymLongForm: String): Flow<AcromineResp?> =
        flow {
            if (acronymLongForm.isEmpty()) {
                emit(null)
            } else {

                emit(dummyData)
            }
        }
}