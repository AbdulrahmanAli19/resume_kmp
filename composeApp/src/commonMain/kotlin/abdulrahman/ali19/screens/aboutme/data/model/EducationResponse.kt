package abdulrahman.ali19.screens.aboutme.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EducationResponse(
    @SerialName("education") val data: List<EducationItemResponse>?
)

@Serializable
data class EducationItemResponse(
    val name: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    val location: String,
    val info: String,
    val department: String,
    val activities: List<Activity>
)

@Serializable
data class Activity(
    val title: String,
    val name: String,
    @SerialName("start_date") val startDate: String,
    val endDate: String
)