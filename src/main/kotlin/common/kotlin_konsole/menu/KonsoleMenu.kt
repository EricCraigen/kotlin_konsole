package common.kotlin_konsole.menu

open class KonsoleMenu(
    private val menuOptions: LinkedHashMap<String, Boolean>,
    override val title: String,
    override val menuTier: MenuTier? = null
): Menu(
    menuOptions
) {
    val backOption: Int = this.size + 1

    override suspend fun callback(menuOption: Int) {
        this.callback(menuOption)
    }

    fun menuToString(isMainMenu: Boolean): String {
        var retStr = this.toString()
        retStr += "\n${this.backOption}. ${if (isMainMenu) "Exit" else "Back"}"
        retStr += "\n\nSelection: "
        return retStr
    }

    override fun toString(): String {
        var optionCtr = 1
        var retStr = "\n$title\n"
        menuOptions.map { option ->
            retStr += "\n${optionCtr++}. ${option.key}"
        }

        return retStr
    }

}