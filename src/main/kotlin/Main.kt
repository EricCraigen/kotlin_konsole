import kotlinx.coroutines.runBlocking
import nfc_playground.NfcPlayground

fun main() {

    try {
        runBlocking {
            val nfcPlayground = NfcPlayground()
            nfcPlayground.run()
        }
    } catch (ex: IllegalStateException) {
        println(ex.message)
    }


}