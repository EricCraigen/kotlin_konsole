package common.kotlin_konsole

enum class ProgramStatus(val value: UByte) {
    UNKNOWN(0x00u),
    RUNNING(0x01u),
    STOPPED(0x02u);

    companion object {
        private val map = values().associateBy(ProgramStatus::value)

        fun fromValue(value: UByte) = map.getValue(value)
    }
}