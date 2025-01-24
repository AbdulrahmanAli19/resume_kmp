package abdulrahman.ali19.screens.education.ui.viewmodel.model


data class EducationState(
    val education: List<EducationItem> = emptyList()
)

data class EducationItem(
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val location: String = "",
    val info: String = "",
    val department: String = "",
    val activities: List<ActivityState> = emptyList()
)


data class ActivityState(
    val title: String = "",
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
)

sealed class EducationEvents{

}