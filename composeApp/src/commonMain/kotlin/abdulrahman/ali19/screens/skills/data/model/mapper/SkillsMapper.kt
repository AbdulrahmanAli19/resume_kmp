package abdulrahman.ali19.screens.skills.data.model.mapper

import abdulrahman.ali19.screens.skills.data.model.SkillsItemResponse
import abdulrahman.ali19.screens.skills.data.model.SkillsResponse
import abdulrahman.ali19.screens.skills.domain.entity.SkillsEntity
import abdulrahman.ali19.screens.skills.domain.entity.SkillsItemEntity

fun SkillsResponse.toEntity(): SkillsEntity {
    return SkillsEntity(
        technicalSkills = technicalSkills?.map { it.toEntity() },
        softwareEngineering = softwareEngineering,
        softSkills = softSkills
    )
}

private fun SkillsItemResponse.toEntity(): SkillsItemEntity {
    return SkillsItemEntity(
        type = type,
        skills = skills
    )
}