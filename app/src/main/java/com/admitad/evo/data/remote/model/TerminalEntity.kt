package com.admitad.evo.data.remote.model

import com.google.gson.annotations.SerializedName

data class TerminalEntity(
    @SerializedName("terminal_id")
    val terminalID: String
)