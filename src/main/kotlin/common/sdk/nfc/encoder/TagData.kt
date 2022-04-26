@file:OptIn(ExperimentalUnsignedTypes::class)

package common.sdk.nfc.encoder

import common.lang.DataArrayable
import java.io.File

class TagData(
    xmlFile: File
): DataArrayable {

    val data: UByteArray by lazy {
        this.transformFileToTagData()
    }

    override fun data(): UByteArray =
        this.data()

    private fun transformFileToTagData(): UByteArray {
        // todo take xml file and turn into TagData Records
        return ubyteArrayOf()
    }

    override fun hashCode() = this.data.hashCode()

    override fun equals(other: Any?) = when {
        this === other -> true
        this.javaClass != other?.javaClass -> false
        else -> this.data.contentEquals((other as TagData).data)
    }

    companion object {
        internal fun parse(xmlFile: File) =
            TagData(xmlFile)
    }


}

