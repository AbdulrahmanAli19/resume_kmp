package abdulrahman.ali19.screens.experience.ui.viewmodel.model.mapper

import abdulrahman.ali19.screens.experience.domain.entity.ExperienceEntity
import abdulrahman.ali19.screens.experience.domain.entity.ProjectEntity
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceState
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceItemState
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ProjectState

fun ExperienceEntity.toState(): ExperienceState {
    return ExperienceState(
        screenTitle = screenTitle ?: "",
        screenSubtitle = screenSubtitle ?: "",
        list = this.experience?.map {
            ExperienceItemState(
                icon = it.icon ?: "",
                title = it.title ?: "",
                company = it.company ?: "",
                startDate = it.startDate ?: "",
                endDate = it.endDate ?: "",
                isCurrent = it.endDate.equals("Current", ignoreCase = true),
                responsibilities = it.responsibilities ?: emptyList(),
                projects = it.projects?.map { projectEntity: ProjectEntity ->
                    ProjectState(
                        name = projectEntity.name ?: "",
                        url = projectEntity.url ?: "",
                        iconUrl = projectEntity.iconUrl ?: "",
                        isClickable = !projectEntity.url.isNullOrBlank()
                    )
                } ?: emptyList()
            )
        } ?: emptyList()
    )
}
