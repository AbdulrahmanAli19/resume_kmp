package abdulrahman.ali19.screens.experience.data.model.mapper

import abdulrahman.ali19.screens.experience.data.model.ExperienceResponse
import abdulrahman.ali19.screens.experience.domain.entity.ExperienceEntity
import abdulrahman.ali19.screens.experience.domain.entity.ExperienceItemEntity
import abdulrahman.ali19.screens.experience.domain.entity.ProjectEntity

fun ExperienceResponse.toEntity(): ExperienceEntity {
    return ExperienceEntity(
        experience = list.map { experienceItem ->
            ExperienceItemEntity(
                title = experienceItem.title,
                company = experienceItem.company,
                startDate = experienceItem.startDate,
                endDate = experienceItem.endDate,
                info = experienceItem.info,
                responsibilities = experienceItem.responsibilities,
                projects = experienceItem.projects?.map { projectItemResponse ->
                    ProjectEntity(
                        name = projectItemResponse.name,
                        url = projectItemResponse.url,
                        iconUrl = projectItemResponse.iconUrl
                    )
                }
            )
        }
    )
}