package abdulrahman.ali19.screens.experience.ui.viewmodel.model.mapper

import abdulrahman.ali19.screens.experience.domain.entity.ExperienceEntity
import abdulrahman.ali19.screens.experience.domain.entity.ProjectEntity
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceItemState
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ProjectState

fun ExperienceEntity.toState() : List<ExperienceItemState> {
    return this.experience?.map {
        ExperienceItemState(
            title = it.title ?: "",
            company = it.company ?: "",
            startDate = it.startDate ?: "",
            endDate = it.endDate ?: "",
            info = it.info ?: "",
            responsibilities = it.responsibilities ?: emptyList(),
            projects = it.projects?.map {projectEntity: ProjectEntity ->
                ProjectState(
                    name = projectEntity.name ?: "",
                    url = projectEntity.url ?: ""
                )
            } ?: emptyList()
        )
    } ?: emptyList()
}