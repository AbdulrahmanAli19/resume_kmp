package abdulrahman.ali19.screens.experience.ui.viewmodel.model

data class ExperienceState(
    val list: List<ExperienceItemState>
)

data class ExperienceItemState(
    val title: String,
    val company: String,
    val startDate: String,
    val endDate: String,
    val info: String,
    val responsibilities: List<String>,
    val projects: List<ProjectState>
)

data class ProjectState(
    val name: String,
    val url: String,
    val iconUrl: String,
    val isClickable: Boolean
)

sealed class ExperienceEvents {
    data class OnProjectClick(val project: ProjectState): ExperienceEvents()

}