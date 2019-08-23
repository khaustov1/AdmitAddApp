package com.admitad.evo.data.repository

interface ReceiptStateRepository {
    fun onReceiptStateChange(receiptState: ReceiptState)
    fun getReceiptState() : ReceiptState
}

enum class ReceiptState {
    RECEIPT_OPENED, POSITION_ADDED, RECEIPT_CLOSED
}