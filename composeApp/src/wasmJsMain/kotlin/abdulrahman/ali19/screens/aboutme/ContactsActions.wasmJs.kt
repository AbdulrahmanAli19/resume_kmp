package abdulrahman.ali19.screens.aboutme

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLDivElement

actual fun copyToClipboard(value: String, showToast: () -> Unit) {
    val clipboard = window.navigator.clipboard
    clipboard.writeText(value)
    showToast()
}

actual fun openNewWindow(url: String) {
    window.open(url, "_blank")
}

actual fun openDialer(phoneNumber: String) {
    val sanitizedNumber = phoneNumber.filter { it.isDigit() || it == '+' }
    val telUrl = "tel:$sanitizedNumber"
    window.open(telUrl, "_blank")
}

actual fun openEmailClient(email: String) {
    val emailUrl = "mailto:$email"
    window.open(emailUrl, "_blank")
}

actual fun showToast(message: String) {
    val toast = document.createElement("div") as HTMLDivElement
    toast.textContent = message
    toast.style.position = "fixed"
    toast.style.bottom = "20px"
    toast.style.left = "50%"
    toast.style.transform = "translateX(-50%)"
    toast.style.backgroundColor = "#323232"
    toast.style.color = "white"
    toast.style.padding = "10px 20px"
    toast.style.borderRadius = "5px"
    toast.style.boxShadow = "0 4px 6px rgba(0, 0, 0, 0.1)"
    toast.style.fontSize = "14px"
    toast.style.zIndex = "1000"
    toast.style.opacity = "0"
    toast.style.transition = "opacity 0.5s ease-in-out"
    toast.style.fontFamily = "'Arial', sans-serif"

    document.body?.appendChild(toast)


    window.setTimeout({
        toast.style.opacity = "1"
        document
    }, 0)

    window.setTimeout({
        toast.style.opacity = "0"

        window.setTimeout({
            document.body?.removeChild(toast)
        }, 500)
        document
    }, 2000)
}