package com.willor.lib_data.domain.dataobjects


import com.google.gson.annotations.SerializedName

data class AcromineRespItem(
    @SerializedName("sf")
    val shortForm: String,

    @SerializedName("lfs")
    val longFormWithVariations: List<LongFormWithVariations>,
)