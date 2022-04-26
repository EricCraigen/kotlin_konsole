package common.kotlin_konsole.menu

enum class MenuTier(val value: Int) {
    MAIN(0),
    SUB_1(1),
    SUB_2(2);

    companion object {
        private val map = values().associateBy(MenuTier::value)

        fun fromValue(value: Int) = map.getValue(value)
    }
}