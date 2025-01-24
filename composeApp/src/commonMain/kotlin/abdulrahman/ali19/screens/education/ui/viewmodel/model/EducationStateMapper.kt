package abdulrahman.ali19.screens.education.ui.viewmodel.model

import abdulrahman.ali19.screens.education.domain.entity.EducationEntity


fun List<EducationEntity>.toEducationState(): List<EducationItem> {
    return this.map {
        EducationItem(
            name = it.name,
            startDate = it.startDate,
            endDate = it.endDate,
            location = it.location,
            info = it.info,
            department = it.department,
            activities = it.activities.map { activity ->
                ActivityState(
                    title = activity.title,
                    name = activity.name,
                    startDate = activity.startDate,
                    endDate = activity.endDate
                )
            }
        )
    }
}