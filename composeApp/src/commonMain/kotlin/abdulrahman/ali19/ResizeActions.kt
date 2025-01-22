package abdulrahman.ali19

expect fun onResizeLayoutChanged(
    mobileLayout: () -> Unit,
    pcLayout: () -> Unit
)

expect fun adjustLayout(
    mobileLayout: () -> Unit,
    pcLayout: () -> Unit
)

expect fun injectStyles()

expect fun setupViewport()