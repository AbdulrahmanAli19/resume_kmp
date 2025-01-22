package abdulrahman.ali19

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLMetaElement
import org.w3c.dom.HTMLStyleElement

actual fun onResizeLayoutChanged(
    mobileLayout: () -> Unit,
    pcLayout: () -> Unit
) {
    window.addEventListener("resize", {
        val isMobile = window.innerWidth <= 768
        if (isMobile) {
            mobileLayout()
        } else {
            pcLayout()
        }
    })
}

actual fun adjustLayout(
    mobileLayout: () -> Unit,
    pcLayout: () -> Unit
) {
    val isMobile = window.innerWidth <= 768
    if (isMobile) {
        mobileLayout()
    } else {
        pcLayout()
    }
}

// In JavaScript-specific code (jsMain)
actual fun injectStyles() {
    val style = document.createElement("style") as HTMLStyleElement
    style.textContent = """
        #container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }

        @media (max-width: 768px) {
            #container {
                flex-direction: column;
                align-items: center;
            }
            #summary {
                text-align: center;
            }
            #contact-info {
                margin-top: 20px;
            }
        }
    """.trimIndent()
    document.head?.appendChild(style)
}

actual fun setupViewport() {
    val meta = document.createElement("meta") as HTMLMetaElement
    meta.setAttribute("name", "viewport")
    meta.setAttribute(
        "content",
        "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
    )
    document.head?.appendChild(meta)
}




