@file:OptIn(ExperimentalUnsignedTypes::class)

package nfc_playground.menus

import common.kotlin_konsole.menu.KonsoleMenu
import common.kotlin_konsole.menu.MenuTier
import common.sdk.nfc.encoder.TagData
import common.sdk.nfc.tags.Tag

class WriteTagsMenu: KonsoleMenu(
    menuOptions,
    title
) {
    val tagsAndDataToWrite: LinkedHashMap<Tag, TagData> = linkedMapOf()

    fun setNumberOfTagsToEncode() {

    }

    fun writeTag(tags: LinkedHashMap<Tag, TagData>) { // todo refactor to take a mutable list of TagData
        tags.map { t ->
            t.key.writeNdefMessage(t.value.data)
        }
    }

    override suspend fun callback(menuOption: Int) {
        when (menuOption) {
            4 -> this.writeTag(this.tagsAndDataToWrite)
        }
    }

    override val menuTier: MenuTier = MenuTier.SUB_1

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