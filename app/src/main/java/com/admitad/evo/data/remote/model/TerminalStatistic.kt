package com.admitad.evo.data.remote.model

import com.google.gson.annotations.SerializedName

data class TerminalStatistic(
    @SerializedName("terminal_id")
    val terminalId : String,
    @SerializedName("printed_blocks")
    val printedBlocks: Int,
    @SerializedName("updated_blocks")
    val updateBlockCounter: Int,
    @SerializedName("update_failures")
    val updateFailureCounter: Int
)