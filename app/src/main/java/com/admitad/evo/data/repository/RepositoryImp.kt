package com.admitad.evo.data.repository

import android.graphics.Bitmap
import com.admitad.evo.data.local.LocalStorage
import com.admitad.evo.data.remote.RemoteAdvBlockDataSource
import com.admitad.evo.data.remote.model.BlockResponse
import com.admitad.evo.data.remote.model.TerminalEntity
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call

class RepositoryImp : Repository, KoinComponent {
    private val remoteAdvBlockDataSource: RemoteAdvBlockDataSource by inject()
    private val localStorage: LocalStorage by inject()

    override fun getTextHeader(): String? = localStorage.getTextHeader()

    override fun getPicture(): Bitmap? = localStorage.getPicture()

    override fun getTextCTA(): String? = localStorage.getTextCTA()

    override fun getOfferLink(): String? = localStorage.getOfferLink()

    override fun getTextFooter(): String? = localStorage.getTextFooter()

    override fun updateTextFooter(textFooter: String) = localStorage.updateTextFooter(textFooter)

    override fun updatePicture(picture: Bitmap) = localStorage.updatePicture(picture)

    override fun updateOfferLink(offerLink: String) = localStorage.updateOfferLink(offerLink)

    override fun updateTextCTA(textCTA: String) = localStorage.updateTextCTA(textCTA)

    override fun updateTextHeader(textHeader: String) = localStorage.updateTextHeader(textHeader)

    override fun getPrintedBlocksCounter(): Int = localStorage.getPrintedBlocksCounter()

    override fun getUpdateBlockCounter(): Int = localStorage.getUpdateBlockCounter()

    override fun getUpdateFailureCounter(): Int = localStorage.getUpdateFailureCounter()

    override fun incrementPrintedBlocksCounter() = localStorage.incrementPrintedBlocksCounter()

    override fun incrementUpdateBlockCounter() = localStorage.incrementUpdateBlockCounter()

    override fun incrementUpdateFailureCounter() = localStorage.incrementUpdateFailureCounter()

    override fun getAdvBlock(terminalEntity: TerminalEntity): Call<BlockResponse> =
        remoteAdvBlockDataSource.getAdbBlock(terminalEntity)
}