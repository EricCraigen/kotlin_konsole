package common.lang

private const val HEX_CHARS = "0123456789ABCDEF"

// Byte -> String (HEX)
// convert to a byte array, then get its hex string
fun Byte.toHex() = byteArrayOf(this).toHex()
fun UByte.toHex() = ubyteArrayOf(this).toHex()

// todo refactor this to have Byte.toHex() have logic

// ByteArray -> String (HEX)
fun ByteArray.toHex(spaces: Boolean = false): String {

    val builder = StringBuilder(this.size * (if (spaces) 3 else 2))

    for (j in this.indices) {
        val v = this[j].toInt() and 0xFF

        builder.append(HEX_CHARS[v ushr 4])
        builder.append(HEX_CHARS[v and 0x0F])

        if (spaces) {
            //hexChars[j * 2 + 2] = ' '
            builder.append(' ')
        }
    }

    return builder.toString().trim()
}
fun UByteArray.toHex(spaces: Boolean = false): String = this.toByteArray().toHex(spaces)

fun List<Byte?>.toHex(spaces: Boolean = false, empty: String = "--"): String =
    this.joinToString(if (spaces) " " else "") { it?.toHex() ?: empty }

fun UByteArray.toHexChunked(chunks: Int, spaces: Boolean = true, indexed: Boolean = true, offset: Int = 0): String =
    this.toByteArray().toHexChunked(chunks, spaces, indexed, offset)

fun ByteArray.toHexChunked(chunks: Int, spaces: Boolean = true, indexed: Boolean = true, offset: Int = 0): String {
    /*
    chunks = 4,
    space = true,
    indexed = true,
    offset = 4

    [04] 01 03 A0 0C
    [05] 34 03 15 D1
    [06] 01 11 54 02
    [07] 65 6E 48 65
     */

    return this.toList().chunked(chunks).mapIndexed { index, chunk ->
        if (indexed) {
            "[${(offset + index).toHex()}] ${chunk.toByteArray().toHex(spaces)}"
        } else {
            chunk.toByteArray().toHex(spaces)
        }
    }.joinToString(System.lineSeparator())
}

fun List<Byte?>.toHexChunked(chunks: Int, spaces: Boolean = true, indexed: Boolean = true, offset: Int = 0): String {
    return this.chunked(chunks).mapIndexed { index, chunk ->
        if (indexed) {
            "[${(offset + index).toHex()}] ${chunk.toHex(spaces)}"
        } else {
            chunk.toHex(spaces)
        }
    }.joinToString(System.lineSeparator())
}

// Int -> String (HEX)
fun Int.toHex() = "%02x".format(this).trim().uppercase()

// HEX -> ByteArray
fun String.fromHex(): ByteArray {
    val data = ByteArray(length / 2)
    val str = this.uppercase()

    for (i in 0 until length step 2) {
        val firstIndex = HEX_CHARS.indexOf(str[i])
        val secondIndex = HEX_CHARS.indexOf(str[i + 1])

        val octet = firstIndex.shl(4).or(secondIndex)
        data[i.shr(1)] = octet.toByte()
    }

    return data
}
fun String.fromHexU(): UByteArray = this.fromHex().toUByteArray()

fun String.fromHexSingle(): Byte {
    require(this.length == 2) { "The hex string must be 2 characters; '$this' is invalid." }
    return this.fromHex().single()
}

fun String.fromHexSingleU(): UByte = this.fromHexSingle().toUByte()

// Long -> String (HEX)
fun Long.toHex() = "%02x".format(this).trim().uppercase()
fun ULong.toHex() = this.toLong().toHex()
