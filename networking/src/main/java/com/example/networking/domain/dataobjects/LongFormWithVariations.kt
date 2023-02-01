package com.willor.lib_data.domain.dataobjects


import com.google.gson.annotations.SerializedName

data class LongFormWithVariations(
    @SerializedName("freq")
    val freq: Int,
    @SerializedName("lf")
    val longForm: String,
    @SerializedName("since")
    val since: Int,
    @SerializedName("vars")
    val variations: List<Variation>
)