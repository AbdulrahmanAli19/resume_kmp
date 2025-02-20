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
    val department: String = "",
    val activities: List<ActivityState> = emptyList(),
    val isActivesVisible: Boolean = activities.isNotEmpty(),
    val projects: List<ProjectState>,
    val isProjectsVisible: Boolean = projects.isNotEmpty(),
)

data class ProjectState(
    val name: String = "",
    val url: String = "",
    val technologies: List<String> = emptyList(),
    val description: String = ""
)

data class CourseState(
    val name: String = "",
    val platform: String = "",
    val endDate: String = "",
    val certificateLink: String? = null,
)

data class ActivityState(
    val title: String = "",
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
)

sealed class EducationEvents {
    data class CopyLink(val link: String) : EducationEvents()
    data class OpenNewWindow(val link: String) : EducationEvents()
}