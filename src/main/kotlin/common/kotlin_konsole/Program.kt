package common.kotlin_konsole

import common.kotlin_konsole.konsole.Konsole
import common.kotlin_konsole.menu.KonsoleMenu

abstract class Program: Konsole {

    override var programStatus: ProgramStatus = ProgramStatus.UNKNOWN
    abstract override val mainMenu: KonsoleMenu

    /**
     * Starts the konsole's running process
     */
    override suspend fun startKonsole(currentMenu: KonsoleMenu) {
        this.programStatus = ProgramStatus.RUNNING
        this.printCurrentMenu(currentMenu)
        this.getUserInputAndResolveKonsole(currentMenu)
    }

    /**
     * Prompts the user to make a selection from the menu and resolves their selection
     */
    override suspend fun getUserInputAndResolveKonsole(currentMenu: KonsoleMenu) {

        while (programStatus == ProgramStatus.RUNNING) {

            val userInput: String? = readLine()

            val menuOption: Int? = this.resolveUserInput(userInput)

            if (menuOption != null) {
                this.kontrolKonsole(menuOption)
            } else {
                this.restartKonsole(currentMenu)
            }
        }
    }

    /**
     * Run the program. Call this from main to start your menu-driven Konsole Program
     */
    override suspend fun run() {
        this.startKonsole(this.mainMenu)
    }

    /**
     * Kontrol the konsole based on the user's menu selection
     */
    override suspend fun kontrolKonsole(menuOption: Int) {
        val isMenuOptionBackOption = isMenuOptionBackOption(menuOption)

        var nextMenu: KonsoleMenu? = null
        var isSubMenu: Boolean? = null

        if (isMenuOptionBackOption) {
            this.backOrKillKonsole()
        } else {
            nextMenu = konsoleMenus[menuOption]
            isSubMenu = isUserMenuSelectionSubMenu(nextMenu, menuOption)
        }

        if (isSubMenu != null) {
            if (nextMenu != null) {
                this.nextMenuOrCallbackOption(isSubMenu, nextMenu, menuOption)
            }
        }
    }

    /**
     * Either restarts the konsole with the nextMenu OR calls on the callback map
     */
    override suspend fun nextMenuOrCallbackOption(isSubMenu: Boolean, nextMenu: KonsoleMenu, menuOption: Int) {
        if (isSubMenu) {
            this.restartKonsole(nextMenu)
        } else {
            this.killKonsole() // todo how to handle...
            this.callbackMap(menuOption, this.currentMenuSelection)
        }
    }
}


