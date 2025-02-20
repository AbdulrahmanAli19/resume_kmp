package abdulrahman.ali19.screens.education.ui.viewmodel.model

import abdulrahman.ali19.screens.education.domain.entity.CourseEntity
import abdulrahman.ali19.screens.education.domain.entity.EducationEntity


fun List<EducationEntity>.toEducationState(): List<EducationItem> {
    return this.map {
        EducationItem(
            name = it.name,
            startDate = it.startDate.plus(" - "),
            endDate = it.endDate,
            location = it.location,
            info = it.info,
            department = it.department,
            activities = it.activities.map { activity ->
                ActivityState(
                    title = activity.title,
                    name = activity.name,
                    startDate = activity.startDate.plus(" - "),
                    endDate = activity.endDate
                )
            },
            projects = it.projects?.map { project ->
                ProjectState(
                    name = project.name,
                    url = project.link,
                    technologies = project.technologies.map { "   - $it" },
                    description = project.description,
                )
            } ?: emptyList()
        )
    }
}

fun List<CourseEntity>.toCourseState(): List<CourseState> {
    return this.map {
        CourseState(
            name = it.name,
            platform = it.platform,
            endDate = it.endDate,
            certificateLink = it.certificateLink
        )
    }
}