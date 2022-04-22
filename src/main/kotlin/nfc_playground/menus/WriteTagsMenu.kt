package nfc_playground.menus

import common.kotlin_konsole.menu.KonsoleMenu

class WriteTagsMenu: KonsoleMenu(
    menuOptions,
    title
) {

    companion object {
        const val title = "Write Tags Menu"

        val menuOptions: KonsoleMenu = KonsoleMenu(
            linkedMapOf(
                ("Set Ndef Record Type" to true),
                ("Set Ndef Record Data" to true),
                ("Set number of tags to encode" to false),
                ("Write Tags" to false)
            ),
            title
        )
    }
}