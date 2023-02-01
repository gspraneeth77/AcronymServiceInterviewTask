package com.example.acronymserviceinterviewtask.mainactivityviewmodel_test

import com.willor.lib_data.domain.dataobjects.AcromineResp
import com.willor.lib_data.domain.dataobjects.AcromineRespItem
import com.willor.lib_data.domain.dataobjects.LongFormWithVariations
import com.willor.lib_data.domain.dataobjects.Variation


val acromineRespItemDummy = AcromineRespItem(
    shortForm = "ADD",
    longFormWithVariations = listOf(
        LongFormWithVariations(
            freq = 1,
            longForm = "Attention Deficit Disorder",
            since = 1999,
            variations = listOf(
                Variation(
                    freq = 12,
                    longForm = "attention-deficit-disorder",
                    usedSince = 1999
                )
            )
        )
    )
)

private val resp = AcromineResp().apply {
    this.add(acromineRespItemDummy)
}

val dummyData = resp

