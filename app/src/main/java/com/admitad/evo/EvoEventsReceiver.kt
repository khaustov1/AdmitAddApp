package com.admitad.evo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.admitad.evo.data.repository.ReceiptState
import com.admitad.evo.data.repository.ReceiptStateRepository
import com.admitad.evo.data.repository.Repository
import com.admitad.evo.presentation.MainService
import org.koin.core.KoinComponent
import org.koin.core.inject

class EvoEventsReceiver : BroadcastReceiver(), KoinComponent {
    private val repository: Repository by inject()
    private val receiptStateRepository: ReceiptStateRepository by inject()

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        Log.e("TAG", action)
        when (action) {
            "evotor.intent.action.cashDrawer.OPEN" -> {

            }
            "evotor.intent.action.cashOperation.CASH_IN" -> {

            }
            "evotor.intent.action.cashOperation.CASH_OUT" -> {

            }
            "evotor.intent.action.receipt.sell.CLEARED" -> {
                //Очистка чека продажи (создание нового чека).
            }
            "evotor.intent.action.receipt.payback.CLEARED" -> {
                //Очистка чека возврата (создание нового чека).
            }
            "evotor.intent.action.receipt.sell.RECEIPT_CLOSED" -> {
                receiptStateRepository.onReceiptStateChange(ReceiptState.RECEIPT_CLOSED)
                if (!shouldPrintBeforeReceipt()) {
                    context.startService(
                        Intent(context, MainService::class.java)
                            .putExtra(MainService.TASK_ID, MainService.PRINT_ADV_BLOCK)
                    )
                }
            }
            "evotor.intent.action.receipt.sell.POSITION_ADDED" -> {
                if (receiptStateRepository.getReceiptState() == ReceiptState.POSITION_ADDED)
                    return
                receiptStateRepository.onReceiptStateChange(ReceiptState.POSITION_ADDED)
                if (shouldPrintBeforeReceipt()) {
                    context.startService(
                        Intent(context, MainService::class.java)
                            .putExtra(MainService.TASK_ID, MainService.PRINT_ADV_BLOCK)
                    )
                }
            }
            "evotor.intent.action.receipt.payback.RECEIPT_CLOSED" -> {
                //Закрытие чека возврата.
            }
            "evotor.intent.action.receipt.sell.OPENED" -> {
                receiptStateRepository.onReceiptStateChange(ReceiptState.RECEIPT_OPENED)
                if (shouldSendStatsAndUpdate()) {
                    context.startService(
                        Intent(context, MainService::class.java)
                            .putExtra(MainService.TASK_ID, MainService.UPDATE_TASK_BLOCK)
                    )
                }
            }
            "evotor.intent.action.receipt.payback.OPENED" -> {
                //Открытие чека возврата.
            }
            "evotor.intent.action.reports.SESSION_CLOSED" -> {
                context.startService(
                    Intent(context, MainService::class.java)
                        .putExtra(MainService.TASK_ID, MainService.UPDATE_TASK_BLOCK)
                )
            }
        }
    }

    private fun shouldPrintBeforeReceipt(): Boolean =
        when (repository.getPlacement()) {
            1 -> false
            2 -> true
            else -> false
        }

    private fun shouldSendStatsAndUpdate(): Boolean = repository.getPrintedBlocksCounter() % RECEIPT_COUNTER_TO_SEND_STATS == 0

    companion object {
        private const val RECEIPT_COUNTER_TO_SEND_STATS = 30
    }

}