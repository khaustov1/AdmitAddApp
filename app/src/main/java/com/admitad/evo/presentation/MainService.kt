package com.admitad.evo.presentation

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import com.admitad.evo.domain.MainPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.evotor.devices.commons.ConnectionWrapper
import ru.evotor.devices.commons.DeviceServiceConnector
import ru.evotor.devices.commons.exception.DeviceServiceException
import ru.evotor.devices.commons.printer.PrinterDocument
import ru.evotor.devices.commons.printer.printable.IPrintable
import ru.evotor.devices.commons.printer.printable.PrintableImage
import ru.evotor.devices.commons.printer.printable.PrintableText
import ru.evotor.devices.commons.services.IPrinterServiceWrapper
import ru.evotor.devices.commons.services.IScalesServiceWrapper

class MainService : IntentService(MainService::javaClass.name), KoinComponent {
    private val mainPresenter: MainPresenter by inject()


    override fun onBind(intent: Intent): IBinder? = null

    override fun onHandleIntent(intent: Intent?) {
        val task = intent?.getIntExtra(TASK_ID, UPDATE_TASK_BLOCK)
        when (task) {
            UPDATE_TASK_BLOCK -> {
                val imei = getImei()
                mainPresenter.updateData(imei)
                mainPresenter.sendStatistics(imei)
            }
            PRINT_ADV_BLOCK -> {
                printAdvBlock()
            }
        }
    }

    private fun printAdvBlock() {
        Log.e("!!!!!!!!!", "PRINTED")
        GlobalScope.launch {
            DeviceServiceConnector.startInitConnections(applicationContext)
            DeviceServiceConnector.addConnectionWrapper(connectionWrapper)
        }
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    private fun getImei(): String {
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        @SuppressLint("HardwareIds") var imei = telephonyManager.deviceId
        if (TextUtils.isEmpty(imei)) {
            imei = "000000000000000"
        }

        return imei
    }

    private fun constructDocument(): PrinterDocument {
        val printableList: MutableList<IPrintable> = ArrayList()
        mainPresenter.getTextHeader()?.let {
            printableList.add(PrintableText(it))
        }
        mainPresenter.getPicture()?.let {
            printableList.add(PrintableImage(it))
        }
        mainPresenter.getTextCTA()?.let {
            printableList.add(PrintableText(it))
        }
        /** Not implemented in current API version
         *
        mainPresenter.getOfferLink()?.let {
            printableList.add(PrintableBarcode("www.google.com", PrintableBarcode.BarcodeType.QR_CODE))
        }
        */
        mainPresenter.getTextFooter()?.let {
            printableList.add(PrintableText(it))
        }
        return PrinterDocument(*printableList.toTypedArray())
    }

    private val connectionWrapper = object : ConnectionWrapper {
        override fun onScalesServiceConnected(scalesService: IScalesServiceWrapper?) {

        }

        override fun onPrinterServiceDisconnected() {

        }

        override fun onScalesServiceDisconnected() {

        }

        override fun onPrinterServiceConnected(printerService: IPrinterServiceWrapper?) {
            GlobalScope.launch {
                try {
                    DeviceServiceConnector.getPrinterService().printDocument(
                        ru.evotor.devices.commons.Constants.DEFAULT_DEVICE_INDEX,
                        constructDocument()
                    )
                    mainPresenter.onReceiptPrinted()
                } catch (e: DeviceServiceException) {
                    e.printStackTrace()
                }
                finally {
                    DeviceServiceConnector.startDeinitConnections()
                    DeviceServiceConnector.clearConnectionWrappers()
                }
            }
        }

    }

    companion object {
        const val TASK_ID = "task_id"
        const val PRINT_ADV_BLOCK = 1
        const val UPDATE_TASK_BLOCK = 2
    }
}
