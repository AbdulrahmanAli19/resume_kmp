package abdulrahman.ali19.screens.education.ui.viewmodel.model


data class EducationState(
    val education: List<EducationItem> = emptyList(),
    val courses: List<CourseState> = emptyList()
)

data class EducationItem(
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val location: String = "",
    val info: String = "",
    val isInfoVisible: Boolean = info.isNotEmpty(),
    val department: String = "",
    val activities: List<ActivityState> = emptyList(),
    val isActivesVisible: Boolean = activities.isNotEmpty(),
    val projects: List<ProjectState>,
    val isProjectsVisible: Boolean = projects.isNotEmpty(),
)

data class ProjectState(
    val name: String = "",
    val url: String = "",
    val technologies: String = "",
    val description: String = ""
)

data class CourseState(
    val name: String = "",
    val platform: String = "",
    val endDate: String = "",
    val certificateLink: String? = null,
    val isClickable: Boolean = certificateLink != null,
    val previewText: String = ""
)

data class ActivityState(
    val title: String = "",
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val info: String,
    val isInfoVisible: Boolean = info.isNotEmpty(),
    val responsibilities: List<String>,
    val isResponsibilitiesVisible: Boolean = responsibilities.isNotEmpty(),
)

sealed class EducationEvents {
    data class CopyLink(val link: String) : EducationEvents()
    data class OpenNewWindow(val link: String) : EducationEvents()
}