package abdulrahman.ali19

import abdulrahman.ali19.di.initKoin
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
