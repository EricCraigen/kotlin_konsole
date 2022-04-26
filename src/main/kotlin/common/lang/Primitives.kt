@file:OptIn(ExperimentalUnsignedTypes::class)

package common.lang

// is every element in the ByteArray a val?
fun ByteArray.isAll(value: Byte) = if (this.isEmpty()) false else this.all { it == value }
fun UByteArray.isAll(value: UByte) = if (this.isEmpty()) false else this.all { it == value }

// is every element in the ByteArray a 0?
fun ByteArray.isAll0s() = this.isAll(0)
fun UByteArray.isAll0s() = this.isAll(0U)

// string to int

fun String?.toIntDefault(default: Int): Int = this.trimNull()?.toIntOrNull() ?: default

// array toInt()

fun UByteArray.toInt(): Int {
    // https://stackoverflow.com/questions/56872782/convert-byte-array-to-int-odd-result-java-and-kotlin
    // https://stackoverflow.com/questions/7619058/convert-a-byte-array-to-integer-in-java-and-vice-versa

    var result = 0
    var shift = Byte.SIZE_BITS * (this.size - 1)
    for (b in this) {
        result = result or (b.toInt() shl shift)
        shift -= Byte.SIZE_BITS
    }

    return result
}
