package com.admitad.evo.data.remote.model

import com.google.gson.annotations.SerializedName

data class BlockEntity(
    @SerializedName("print_block")
    val printBlock : Boolean,
    @SerializedName("text_header")
    val textHeader : String?,
    @SerializedName("picture_link")
    val pictureLink: String,
    @SerializedName("text_CTA")
    val textCTA: String?,
    @SerializedName("offer_link")
    val offerLink: String,
    @SerializedName("text_footer")
    val textFooter: String?
)

data class BlockResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val blockEntity: BlockEntity
)