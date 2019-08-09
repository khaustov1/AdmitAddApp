package com.admitad.evo.data.repository

import android.graphics.Bitmap
import com.admitad.evo.data.remote.model.BlockResponse
import com.admitad.evo.data.remote.model.TerminalEntity
import retrofit2.Call

interface Repository {

    fun getTextHeader(): String?

    fun getPicture(): Bitmap?

    fun getTextCTA(): String?

    fun getOfferLink(): String?

    fun getTextFooter(): String?

    fun getAdvBlock(terminalEntity: TerminalEntity): Call<BlockResponse>

    fun updatePicture(picture : Bitmap)

    fun updateTextHeader(textHeader: String)

    fun updateTextCTA(textCTA: String)

    fun updateOfferLink(offerLink: String)

    fun updateTextFooter(textFooter: String)

    fun getPrintedBlocksCounter() : Int

    fun incrementPrintedBlocksCounter()

    fun getUpdateBlockCounter() : Int

    fun incrementUpdateBlockCounter()

    fun getUpdateFailureCounter() : Int

    fun incrementUpdateFailureCounter()
}