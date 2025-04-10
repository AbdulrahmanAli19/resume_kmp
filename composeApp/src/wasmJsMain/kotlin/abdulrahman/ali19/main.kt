package abdulrahman.ali19

import abdulrahman.ali19.di.initKoin
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.events.Event

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    ComposeViewport(document.body!!) {
        window.document.title = "Abdulrahman Ali"
        App()
    }
    window.dispatchEvent(Event("ComposeReady"))

}