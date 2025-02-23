package abdulrahman.ali19.screens.education.domain.entity

data class EducationEntity(
    val name: String,
    val startDate: String,
    val endDate: String,
    val location: String,
    val info: String,
    val department: String,
    val activities: List<ActivityEntity>,
    val projects: List<ProjectEntity>?
)

data class ProjectEntity(
    val name: String,
    val description: String,
    val link: String,
    val technologies: List<String>
)

data class ActivityEntity(
    val title: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val info: String,
    val responsibilities: List<String>
)

data class CourseEntity(
    val name: String,
    val platform: String,
    val endDate: String,
    val certificateLink: String?
)