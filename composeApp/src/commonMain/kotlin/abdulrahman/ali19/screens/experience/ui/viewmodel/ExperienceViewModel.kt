package abdulrahman.ali19.screens.experience.ui.viewmodel

import abdulrahman.ali19.core.BaseViewModel
import abdulrahman.ali19.screens.aboutme.openNewWindow
import abdulrahman.ali19.screens.experience.domain.uasecase.GatExperienceUseCase
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceEvents
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceState
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.mapper.toState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExperienceViewModel(
    private val gatExperienceUseCase: GatExperienceUseCase
) : BaseViewModel<ExperienceState, ExperienceEvents>(initialState = ExperienceState(list = emptyList())) {

    init {
        viewModelScope.launch {
            val result = gatExperienceUseCase()
            _state.update {
                it.copy(list = result.toState())
            }
        }
    }

    override fun sendEvent(event: ExperienceEvents) {
        when (event) {
            is ExperienceEvents.OnProjectClick -> openNewWindow(event.project.url)
        }
    }

}