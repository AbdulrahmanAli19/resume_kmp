package abdulrahman.ali19.screens.experience.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceResponse(
    @SerialName("experience") val list: List<ExperienceItemResponse>
)

@Serializable
data class ExperienceItemResponse(
    val title: String?,
    val company: String?,
    @SerialName("start_date") val startDate: String?,
    @SerialName("end_date") val endDate: String?,
    val info: String?,
    val responsibilities: List<String>?,
    val projects: List<ProjectItemResponse>?
)

@Serializable
data class ProjectItemResponse(
    val name: String?,
    val url: String?,
    @SerialName("icon") val iconUrl: String?
)