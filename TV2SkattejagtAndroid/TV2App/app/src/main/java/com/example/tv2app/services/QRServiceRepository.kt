package com.example.tv2app.services

class QRServiceRepository {

    var scannedTaskId : String = ""
    var scannedQRContents : String = ""


    fun saveQRContent(qr : String) : String {
        scannedQRContents = qr
        return scannedQRContents
    }

    fun splitQRCode(qrContents: String) : String {

        val split = qrContents.split(",")
        scannedTaskId = split[0]
        val type = split[1]
        return type

    }

}


