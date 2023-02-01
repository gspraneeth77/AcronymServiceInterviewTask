package com.willor.lib_data.domain.dataobjects


import com.google.gson.annotations.SerializedName

data class Variation(
    @SerializedName("freq")
    val freq: Int,
    @SerializedName("lf")
    val longForm: String,
    @SerializedName("since")
    val usedSince: Int
)