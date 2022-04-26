package nfc_playground.menus

import common.kotlin_konsole.menu.KonsoleMenu
import common.kotlin_konsole.menu.MenuTier
import kotlinx.coroutines.delay

class ReadMenu: KonsoleMenu(
    menuOptions,
    title
) {

    override val menuTier: MenuTier = MenuTier.SUB_1

    private suspend fun startReadingTags() {
        println("Reading")
        delay(1000L)
    }

    override suspend fun callback(menuOption: Int) {
        when (menuOption) {
            1 -> this.startReadingTags()
        }
    }

    companion object {
        const val title = "Read Tags Menu"

        val menuOptions: KonsoleMenu = KonsoleMenu(
            linkedMapOf(
                ("Start Reading" to false)
            ),
            title
        )
    }
}