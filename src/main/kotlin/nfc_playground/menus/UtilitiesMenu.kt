package nfc_playground.menus

import common.kotlin_konsole.menu.KonsoleMenu

class UtilitiesMenu: KonsoleMenu(
    menuOptions,
    title
) {

    companion object {
        const val title = "Utilities Menu"

        val menuOptions: KonsoleMenu = KonsoleMenu(
            linkedMapOf(
                ("Select Device" to true),
                ("Select Connection Type" to true),
                ("Select Tag Type" to true),
                ("Erase Tags" to false),
                ("Format Tags" to false)
            ),
            title
        )
    }
}