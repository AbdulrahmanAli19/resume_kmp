package abdulrahman.ali19.screens.aboutme

expect fun copyToClipboard(value: String, showToast:() -> Unit)

expect fun openNewWindow(url: String)

expect fun openDialer(phoneNumber: String)

expect fun openEmailClient(email: String)

expect fun showToast(message: String)