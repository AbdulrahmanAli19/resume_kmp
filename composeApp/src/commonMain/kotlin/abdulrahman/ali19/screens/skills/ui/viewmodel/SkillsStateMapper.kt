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
        name = iconName.first,
        skills = skills?.map { "- $it" } ?: emptyList()
    )
}

private fun getIconName(type: String): Pair<String, String> {
    return when (type.lowercase()) {
        "technologies" -> Pair(type, "drawable/technologies.svg")
        "languages" -> Pair(type, "drawable/languages.svg")
        "reactive" -> Pair(type, "drawable/asynchronous_programming.svg")
        "database" -> Pair(type, "drawable/database_skill.svg")
        "ui & design" -> Pair(type, "drawable/brush.svg")
        "networking" -> Pair(type, "drawable/api.svg")
        "security" -> Pair(type, "drawable/security_skill.svg")
        "cloud services" -> Pair(type, "drawable/cloud.svg")
        "testing" -> Pair(type, "drawable/test.svg")
        "di frameworks" -> Pair(type, "drawable/layers.svg")
        "memory" -> Pair(type, "drawable/memory.png")
        else -> Pair("", "")
    }
}