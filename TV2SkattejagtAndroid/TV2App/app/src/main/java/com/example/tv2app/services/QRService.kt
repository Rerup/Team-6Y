package com.example.tv2app.services

class QRService {

    var _scannedTaskId : String = ""
    var _scannedQRContents : String = ""


    fun saveQRContent(qr : String) {
        _scannedQRContents = qr
    }

    fun splitQRCode(qrContents: String) : String {

        val split = qrContents.split(",")
        _scannedTaskId = split[0]
        val type = split[1]
        return type

    }

}