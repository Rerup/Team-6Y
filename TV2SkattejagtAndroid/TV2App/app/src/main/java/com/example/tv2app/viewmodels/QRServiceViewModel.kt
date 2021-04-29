package com.example.tv2app.viewmodels

import androidx.lifecycle.ViewModel
import com.example.tv2app.services.QRServiceRepository

class QRServiceViewModel(private val qrServiceRepository : QRServiceRepository) : ViewModel() {

    private var scannedTaskId : String = ""
    private var scannedQRContents : String = ""

    fun saveQRContent(qr : String){
        scannedQRContents = qrServiceRepository.saveQRContent(qr)
    }

    fun splitQRCode(qrContents : String) : String {
        scannedTaskId = qrServiceRepository.scannedTaskId
        return qrServiceRepository.splitQRCode(qrContents)
    }


}