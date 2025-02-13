package abdulrahman.ali19.screens.skills.ui.viewmodel

import abdulrahman.ali19.screens.skills.domain.entity.SkillsEntity
import abdulrahman.ali19.screens.skills.domain.entity.SkillsItemEntity

fun SkillsEntity.toSkillsState(): SkillsState {
    return SkillsState(
        technicalSkills = technicalSkills?.map { it.toSkillsItemState() } ?: emptyList(),
        softwareEngineering = softwareEngineering ?: emptyList(),
        softSkills = softSkills ?: emptyList()
    )
}

fun SkillsItemEntity.toSkillsItemState(): SkillsItemState {
    return SkillsItemState(
        type = type ?: "",
        skills = skills ?: emptyList()
    )
}