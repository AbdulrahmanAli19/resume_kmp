package abdulrahman.ali19.screens.skills.ui.viewmodel

import abdulrahman.ali19.core.BaseViewModel
import abdulrahman.ali19.screens.skills.domain.uasecase.GatSkillsUseCase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SkillsViewModel(
    private val gatSkillsUseCase: GatSkillsUseCase
) : BaseViewModel<SkillsState, SkillsEvents>(SkillsState()) {
    init {
        viewModelScope.launch {
            val skills = gatSkillsUseCase()
            _state.update {
                skills.toSkillsState()
            }
        }
    }

    override fun sendEvent(event: SkillsEvents) {

    }

}