package com.admitad.evo.data.repository

class ReceiptStateRepositoryImp : ReceiptStateRepository {
    private var state: ReceiptState = ReceiptState.RECEIPT_CLOSED

    override fun onReceiptStateChange(receiptState: ReceiptState) {
        state = receiptState
    }

    override fun getReceiptState(): ReceiptState {
        return state
    }
}