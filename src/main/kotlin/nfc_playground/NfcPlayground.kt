package nfc_playground

import common.kotlin_konsole.menu.KonsoleMenu
import common.kotlin_konsole.Program
import nfc_playground.menus.MainMenu
import nfc_playground.menus.ReadMenu
import nfc_playground.menus.UtilitiesMenu
import nfc_playground.menus.WriteTagsMenu

class NfcPlayground: Program() {

    override val mainMenu: KonsoleMenu = mainMenu()

    override val konsoleMenus: MutableList<KonsoleMenu> = konsoleMenus()

    override var currentMenuSelection: KonsoleMenu = mainMenu
    override var previousMenuSelection: KonsoleMenu = mainMenu

    override suspend fun backOrKillKonsole() {
        if (this.currentMenuSelection is MainMenu) {
            this.killKonsole()
        } else {
            this.currentMenuSelection = this.previousMenuSelection
            this.restartKonsole(this.currentMenuSelection)
        }
    }

    override suspend fun callbackMap(menuOption: Int, currentMenuSelection: KonsoleMenu) {
        val mainMenu: KonsoleMenu = this.mainMenu
        val readMenu: KonsoleMenu = this.konsoleMenus[1]

        when (currentMenuSelection) {
            mainMenu -> {
                (mainMenu as MainMenu).callback(menuOption)
            }
            readMenu -> {
                (readMenu as ReadMenu).callback(menuOption)
            }
        }

    }

    companion object {

        private val mainMenu: MainMenu = MainMenu()

        private val readMenu: ReadMenu = ReadMenu()
        private val writeTagsMenu: WriteTagsMenu = WriteTagsMenu()
        private val utilitiesMenu: UtilitiesMenu = UtilitiesMenu()
        private val emulationMenu: UtilitiesMenu = UtilitiesMenu()

        private val konsoleMenus: MutableList<KonsoleMenu> = mutableListOf(
            mainMenu,
            readMenu,
            writeTagsMenu,
            utilitiesMenu,
            emulationMenu
        )

        fun konsoleMenus(): MutableList<KonsoleMenu> {
            return konsoleMenus
        }

        fun mainMenu(): KonsoleMenu {
            return mainMenu
        }

    }
}