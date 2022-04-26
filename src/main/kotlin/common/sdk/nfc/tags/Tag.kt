@file:OptIn(ExperimentalUnsignedTypes::class)

package common.sdk.nfc.tags

import common.sdk.nfc.reports.EncodingReport

class Tag(

) {

    fun writeNdefMessage(data: UByteArray): EncodingReport {
        val cc = ubyteArrayOf(
            data[0],
            data[1],
            data[2],
            data[3]
        )

        val tlv = data.takeLast(data.size - 4).toUByteArray()
        this.writeCC(cc)
        this.writeTLV(tlv)

        return EncodingReport(true)
    }

    private fun writeCC(cc: UByteArray) {
        println("Writing $cc")
    }

    private fun writeTLV(tlv: UByteArray) {
        println("Writing $tlv")
    }

}