package abdulrahman.ali19.screens.education.data.model.mappers

import abdulrahman.ali19.screens.education.data.model.EducationResponse
import abdulrahman.ali19.screens.education.domain.entity.ActivityEntity
import abdulrahman.ali19.screens.education.domain.entity.CourseEntity
import abdulrahman.ali19.screens.education.domain.entity.EducationEntity

fun EducationResponse.toEntity() = data?.map {
    EducationEntity(
        name = it.name,
        startDate = it.startDate,
        endDate = it.endDate,
        location = it.location,
        info = it.info,
        department = it.department,
        activities = it.activities.map { activity ->
            ActivityEntity(
                title = activity.title,
                name = activity.name,
                startDate = activity.startDate,
                endDate = activity.endDate,
            )
        },
    )
} ?: emptyList()

fun EducationResponse.toCourseEntity() = courses?.map {
    CourseEntity(
        name = it.name ?: "",
        platform = it.platform ?: "",
        endDate = it.endDate ?: "",
        certificateLink = it.certificateLink,
    )
} ?: emptyList()