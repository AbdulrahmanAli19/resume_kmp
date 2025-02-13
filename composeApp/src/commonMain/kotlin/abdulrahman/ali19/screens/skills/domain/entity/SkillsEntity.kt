package abdulrahman.ali19.screens.skills.domain.entity


data class SkillsEntity(
    val technicalSkills: List<SkillsItemEntity>?,
    val softwareEngineering: List<String>?,
    val softSkills: List<String>?,
)

data class SkillsItemEntity(
    val type: String?,
    val skills: List<String>?,
)