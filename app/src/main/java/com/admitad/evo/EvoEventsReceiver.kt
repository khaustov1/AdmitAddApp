package com.admitad.evo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.admitad.evo.presentation.MainService

class EvoEventsReceiver : BroadcastReceiver() {

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
                context.startService(
                    Intent(context, MainService::class.java)
                        .putExtra(MainService.TASK_ID, MainService.PRINT_ADV_BLOCK)
                )
            }
            "evotor.intent.action.receipt.payback.RECEIPT_CLOSED" -> {
                //Закрытие чека возврата.
            }
            "evotor.intent.action.receipt.sell.OPENED" -> {
                context.startService(
                    Intent(context, MainService::class.java)
                        .putExtra(MainService.TASK_ID, MainService.UPDATE_TASK_BLOCK)
                )
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
}