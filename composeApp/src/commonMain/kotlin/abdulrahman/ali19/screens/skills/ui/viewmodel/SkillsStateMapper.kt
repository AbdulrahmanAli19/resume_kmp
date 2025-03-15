package abdulrahman.ali19.screens.skills.ui.viewmodel

import abdulrahman.ali19.screens.skills.domain.entity.SkillsEntity
import abdulrahman.ali19.screens.skills.domain.entity.SkillsItemEntity
import org.jetbrains.compose.resources.DrawableResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.api
import resume.composeapp.generated.resources.asynchronous_programming
import resume.composeapp.generated.resources.brush
import resume.composeapp.generated.resources.cloud
import resume.composeapp.generated.resources.database_skill
import resume.composeapp.generated.resources.languages
import resume.composeapp.generated.resources.layers
import resume.composeapp.generated.resources.memory
import resume.composeapp.generated.resources.security_skill
import resume.composeapp.generated.resources.technologies
import resume.composeapp.generated.resources.test

fun SkillsEntity.toSkillsState(): SkillsState {
    return SkillsState(
        technicalSkills = technicalSkills?.map { it.toSkillsItemState() } ?: emptyList(),
        softwareEngineering = softwareEngineering ?: emptyList(),
        softSkills = softSkills ?: emptyList()
    )
}

fun SkillsItemEntity.toSkillsItemState(): SkillsItemState {
    val iconName: Pair<String, DrawableResource?> = getIconName(type ?: "")
    return SkillsItemState(
        icon = iconName.second,
        name = iconName.first,
        skills = skills?.map { "- $it" } ?: emptyList()
    )
}

private fun getIconName(type: String): Pair<String, DrawableResource?> {
    return when (type.lowercase()) {
        "technologies" -> Pair(type, Res.drawable.technologies)
        "languages" -> Pair(type, Res.drawable.languages)
        "reactive" -> Pair(type, Res.drawable.asynchronous_programming)
        "database" -> Pair(type, Res.drawable.database_skill)
        "ui & design" -> Pair(type,  Res.drawable.brush)
        "networking" -> Pair(type, Res.drawable.api)
        "security" -> Pair(type, Res.drawable.security_skill)
        "cloud services" -> Pair(type, Res.drawable.cloud)
        "testing" -> Pair(type, Res.drawable.test)
        "di frameworks" -> Pair(type, Res.drawable.layers)
        "memory" -> Pair(type, Res.drawable.memory)
        else -> Pair("", null)
    }
}