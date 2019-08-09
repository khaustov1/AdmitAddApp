package com.admitad.evo.data.local

import android.graphics.Bitmap

interface LocalStorage {

    fun getPicture(): Bitmap?

    fun getTextHeader(): String?

    fun getTextCTA(): String?

    fun getOfferLink(): String?

    fun getTextFooter(): String?

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