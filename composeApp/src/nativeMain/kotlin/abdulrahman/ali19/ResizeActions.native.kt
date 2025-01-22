package abdulrahman.ali19




actual fun onResizeLayoutChanged(
    mobileLayout: () -> Unit,
    pcLayout: () -> Unit
) {
    mobileLayout()
}

actual fun adjustLayout(
    mobileLayout: () -> Unit,
    pcLayout: () -> Unit
) {
    mobileLayout()
}

actual fun injectStyles() = Unit

actual fun setupViewport() = Unit

