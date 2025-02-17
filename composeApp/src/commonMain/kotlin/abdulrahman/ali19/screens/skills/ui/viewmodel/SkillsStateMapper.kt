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
    val iconName: Pair<String, String> = getIconName(type ?: "")
    return SkillsItemState(
        iconPath = iconName.second,
        name = iconName.second,
        skills = skills?.mapIndexed { index, s -> "${index + 1}.$s" } ?: emptyList()
    )
}

private fun getIconName(type: String): Pair<String, String> {
    return when (type) {
        "technologies" -> Pair(type, "drawable/technologies.svg")
        "languages" -> Pair(type, "drawable/languages.svg")
        "asynchronous programming" -> Pair(type, "drawable/asynchronous_programming.svg")
        "database" -> Pair(type, "drawable/database_skill.svg")
        "brush" -> Pair(type, "drawable/brush.svg")
        "api" -> Pair(type, "drawable/api.svg")
        "security" -> Pair(type, "drawable/security_skill.svg")
        "cloud" -> Pair(type, "drawable/cloud.svg")
        "testing" -> Pair(type, "drawable/test.svg")
        "layers" -> Pair(type, "drawable/layers.svg")
        "memory" -> Pair(type, "drawable/memory.png")
        else -> Pair("", "")
    }
}