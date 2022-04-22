package common.kotlin_konsole.menu

sealed class Menu(
    menuOptions: LinkedHashMap<String, Boolean>
): LinkedHashMap<String, Boolean>(
    menuOptions
) {
    abstract val title: String
    abstract suspend fun callback(menuOption: Int)
}