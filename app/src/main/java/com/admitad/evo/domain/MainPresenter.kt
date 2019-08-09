package com.admitad.evo.domain

import android.graphics.Bitmap

interface MainPresenter {
    fun updateData(terminalId: String)

    fun sendStatistics(terminalId: String)

    fun getTextHeader(): String?

    fun getPicture(): Bitmap?

    fun getTextCTA(): String?

    fun getOfferLink(): String?

    fun getTextFooter(): String?

    fun onReceiptPrinted()

    fun getPrintedBlocksCounter() : Int
}