package nfc_playground.menus

import common.kotlin_konsole.menu.KonsoleMenu

class EmulationMenu: KonsoleMenu(
    menuOptions,
    title
) {

    companion object {
        const val title = "Emulation Menu"

        val menuOptions: KonsoleMenu = KonsoleMenu(
            linkedMapOf(
                ("Select Device" to true),
                ("Select Connection Type" to true),
                ("Select Tag Type" to true),
                ("Start Emulation" to false)
            ),
            title
        )
    }
}