package common.kotlin_konsole.konsole

import common.kotlin_konsole.menu.KonsoleMenu
import common.kotlin_konsole.ProgramStatus
import nfc_playground.menus.MainMenu

interface Konsole {

    var programStatus: ProgramStatus
    val konsoleMenus: MutableList<KonsoleMenu>
    val mainMenu: KonsoleMenu
    var currentMenuSelection: KonsoleMenu
    var previousMenuSelection: KonsoleMenu

    // Region Program Abstraction

    suspend fun backOrKillKonsole()

    suspend fun callbackMap(menuOption: Int, currentMenuSelection: KonsoleMenu)

    suspend fun kontrolKonsole(menuOption: Int)

    suspend fun nextMenuOrCallbackOption(isSubMenu: Boolean, nextMenu: KonsoleMenu, menuOption: Int)

    suspend fun getUserInputAndResolveKonsole(currentMenu: KonsoleMenu)

    suspend fun run()

    suspend fun startKonsole(currentMenu: KonsoleMenu)

    // End Region

    // Region Concrete Functionality

    /**
     * Resolves user menu selection to determine if a submenu was selected, or if a callback needs to be called upon
     */
    suspend fun isUserMenuSelectionSubMenu(nextMenu: KonsoleMenu, userInput: Int): Boolean {
        val currentMenuTypedArray = this.currentMenuSelection.entries.toTypedArray() //
        this.previousMenuSelection = this.currentMenuSelection
        this.currentMenuSelection = nextMenu
        val nextMenuOption = currentMenuTypedArray[userInput - 1]

        return nextMenuOption.value
    }

    /**
     * Sets programStatus to STOPPED in order to "kill' the konsole's while loop
     */
    fun killKonsole() {
        this.programStatus = ProgramStatus.STOPPED
    }

    /**
     * Resolve user's input to Int or null
     */
    fun resolveUserInput(userInput: String?): Int? {
        return userInput?.toIntOrNull()
    }

    /**
     * Restarts the konsole
     */
    suspend fun restartKonsole(currentMenu: KonsoleMenu) {
        this.programStatus = ProgramStatus.RUNNING
        this.printCurrentMenu(currentMenu)
        this.getUserInputAndResolveKonsole(currentMenu)
    }

    /**
     * Determines in the user's menu selection is the current menu selections backOption
     */
    fun isMenuOptionBackOption(menuOption: Int): Boolean {
        val currentMenuBackOption: Int = this.currentMenuSelection.backOption
        return menuOption == currentMenuBackOption
    }

    /**
     * Print the currentMenuSelection with the correct back/exit option printed
     */
    fun printCurrentMenu(currentMenu: KonsoleMenu) {

        if (this.currentMenuSelection is MainMenu) {
            print(currentMenu.menuToString(true))
        } else {
            print(currentMenu.menuToString(false))
        }
    }

    // End Region
}

