package abdulrahman.ali19.screens.aboutme.domain.model

data class EducationEntity(
    val name: String,
    val startDate: String,
    val endDate: String,
    val location: String,
    val info: String,
    val department: String,
    val activities: List<ActivityEntity>
)

data class ActivityEntity(
    val title: String,
    val name: String,
    val startDate: String,
    val endDate: String
)
