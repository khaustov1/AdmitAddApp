package com.admitad.evo.data.remote.model

import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("success")
    val success: Boolean
)