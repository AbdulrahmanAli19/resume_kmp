package abdulrahman.ali19.screens.skills.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkillsResponse(
    @SerialName("technical_skills") val technicalSkills: List<SkillsItemResponse>?,
    @SerialName("software_engineering") val softwareEngineering: List<String>?,
    @SerialName("soft_skills") val softSkills: List<String>?,
)

@Serializable
data class SkillsItemResponse(
    val type: String?,
    val skills: List<String>?,
)