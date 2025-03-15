package abdulrahman.ali19

import abdulrahman.ali19.core.BaseViewModel
import kotlinx.coroutines.flow.update

class AppViewModel : BaseViewModel<AppState, AppEvents>(AppState(isMobile = true)) {

    override fun sendEvent(event: AppEvents) {
        when (event) {
            is AppEvents.UpdateViewPort -> _state.update {
                it.copy(
                    isMobile = event.isMobile,
                    horizontalPadding = if (!event.isMobile) 120 else 16,
                    verticalPadding = if (!event.isMobile) 60 else 7,
                )
            }

            is AppEvents.SelectTab -> _state.update {
                it.copy(selectedTabIndex = event.index)
            }
        }
    }

}

data class AppState(
    val isMobile: Boolean,
    val horizontalPadding: Int = if (!isMobile) 120 else 16,
    val verticalPadding: Int = if (!isMobile) 60 else 7,
    val selectedTabIndex: Int = 0
)

sealed class AppEvents {
    data class UpdateViewPort(val isMobile: Boolean) : AppEvents()
    data class SelectTab(val index: Int) : AppEvents()
}