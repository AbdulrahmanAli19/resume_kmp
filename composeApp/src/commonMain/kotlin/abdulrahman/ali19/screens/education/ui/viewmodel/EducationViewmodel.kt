package abdulrahman.ali19.screens.education.ui.viewmodel

import abdulrahman.ali19.core.BaseViewModel
import abdulrahman.ali19.screens.education.domain.usecase.GetCoursesUseCase
import abdulrahman.ali19.screens.education.domain.usecase.GetEducationUseCase
import abdulrahman.ali19.screens.education.ui.viewmodel.model.EducationEvents
import abdulrahman.ali19.screens.education.ui.viewmodel.model.EducationState
import abdulrahman.ali19.screens.education.ui.viewmodel.model.toCourseState
import abdulrahman.ali19.screens.education.ui.viewmodel.model.toEducationState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EducationViewmodel(
    private val getEducationUseCase: GetEducationUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
) : BaseViewModel<EducationState, EducationEvents>(initialState = EducationState()) {

    init {
        viewModelScope.launch {
            val getEducationUseCase = getEducationUseCase()
            _state.update {
                it.copy(education = getEducationUseCase.toEducationState())
            }
        }
        viewModelScope.launch {
            val getCoursesUseCase = getCoursesUseCase()
            _state.update {
                it.copy(courses = getCoursesUseCase.toCourseState())
            }
        }
    }

    override fun sendEvent(event: EducationEvents) {

    }
}