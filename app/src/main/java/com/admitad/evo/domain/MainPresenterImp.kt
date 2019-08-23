package com.admitad.evo.domain

import android.graphics.Bitmap
import android.util.Log
import com.admitad.evo.data.remote.RemoteStatisticsService
import com.admitad.evo.data.remote.model.TerminalEntity
import com.admitad.evo.data.remote.model.TerminalStatistic
import com.admitad.evo.data.repository.Repository
import com.admitad.evo.utils.BitmapUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenterImp : KoinComponent, MainPresenter {

    private val repository: Repository by inject()
    private val statisticsService: RemoteStatisticsService by inject()

    override fun updateData(terminalId: String) {
        GlobalScope.launch {
            try {
                repository.getAdvBlock(TerminalEntity(terminalId)).execute().body()?.blockEntity?.let { block ->
                    block.textHeader?.let { repository.updateTextHeader(it) }
                    block.offerLink.let { repository.updateOfferLink(it) }
                    block.textFooter?.let { repository.updateTextFooter(it) }
                    block.textCTA?.let { repository.updateTextCTA(it) }
                    block.pictureLink.let { pictureLink ->
                        BitmapUtils.getImageFromURL(pictureLink)?.let {
                            repository.updatePicture(it)
                        }
                    }
                    block.blockPlacement?.let { placement ->
                        repository.updatePlacement(placement)
                    }
                    repository.incrementUpdateBlockCounter()
                } ?: let {
                    repository.incrementUpdateFailureCounter()
                }
            } catch (e: Exception) {
                repository.incrementUpdateFailureCounter()
            }
        }
    }

    override fun sendStatistics(terminalId: String) {
        try {
            GlobalScope.launch {
                val stats = TerminalStatistic(
                    terminalId,
                    repository.getPrintedBlocksCounter(),
                    repository.getUpdateBlockCounter(),
                    repository.getUpdateFailureCounter()
                )
                statisticsService.sendStats(stats).execute()
            }
        } catch (e: Exception) {
            Log.e(this::javaClass.name, e.message)
        }
    }

    override fun onReceiptPrinted() = repository.incrementPrintedBlocksCounter()

    override fun getTextHeader(): String? = repository.getTextHeader()

    override fun getPicture(): Bitmap? = repository.getPicture()

    override fun getTextCTA(): String? = repository.getTextCTA()

    override fun getOfferLink(): String? = repository.getOfferLink()

    override fun getTextFooter(): String? = repository.getTextFooter()

    override fun getPrintedBlocksCounter(): Int = repository.getPrintedBlocksCounter()
}