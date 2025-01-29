package abdulrahman.ali19.screens.experience.domain.entity

data class ExperienceEntity (
    val experience: List<ExperienceItemEntity>? = null
)

data class ExperienceItemEntity(
    val title: String?,
    val company: String?,
    val startDate: String?,
    val endDate: String?,
    val info: String?,
    val responsibilities: List<String>?,
    val projects: List<ProjectEntity>?
)

data class ProjectEntity(
    val name: String?,
    val url: String?
)
