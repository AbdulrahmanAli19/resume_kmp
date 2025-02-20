package abdulrahman.ali19.screens.education.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EducationResponse(
    @SerialName("education") val data: List<EducationItemResponse>?,
    @SerialName("courses") val courses: List<CourseResponse>?
)

@Serializable
data class CourseResponse(
    val name: String?,
    val platform: String?,
    @SerialName("end_date") val endDate: String?,
    @SerialName("certificate_link") val certificateLink: String? = null
)

@Serializable
data class EducationItemResponse(
    val name: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    val location: String,
    val info: String,
    val department: String,
    val activities: List<Activity>,
    val projects: List<ProjectResponse>?
)

@Serializable
data class ProjectResponse(
    val name: String?,
    val description: String?,
    val link: String?,
    val technologies: List<String>?
)
@Serializable
data class Activity(
    val title: String,
    val name: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String
)