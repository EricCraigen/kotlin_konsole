package nfc_playground.menus

import common.kotlin_konsole.menu.KonsoleMenu
import common.kotlin_konsole.menu.MenuTier

class MainMenu: KonsoleMenu(
    menuOptions,
    title
) {

    override val menuTier: MenuTier = MenuTier.MAIN

    override suspend fun callback(menuOption: Int) {
        when (menuOption) {
            1 -> println("Main Menu Calling Back...")
        }
    }

    companion object {
        const val title = "Main Menu"

        val menuOptions: KonsoleMenu = KonsoleMenu(
            linkedMapOf(
                ("Read Tags" to true),
                ("Write Tags" to true),
                ("Utilities" to true),
                ("Emulation" to true),
            ),
            title
        )
    }
}