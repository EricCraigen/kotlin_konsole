@file:OptIn(ExperimentalUnsignedTypes::class)

package common.lang

abstract class DataArray(val data: UByteArray): DataArrayable {

    override fun data(): UByteArray =
        this.data

    // cache toString()
    private val str: String by lazy { this.data.toHex() }
    override fun toString() = str

    override fun hashCode() = this.data.hashCode()

    override fun equals(other: Any?) = when {
        this === other -> true
        this.javaClass != other?.javaClass -> false
        else -> this.data.contentEquals((other as DataArray).data)
    }
}

interface DataArrayable {
    fun data(): UByteArray

    val size: Int
        get() = this.data().size

    val isAll0s: Boolean
        get() = this.data().isAll0s()

    fun toHex(spaces: Boolean = false) : String =
        this.data().toHex(spaces)
}