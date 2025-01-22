package abdulrahman.ali19.screens.aboutme.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalInformationResponse(
    @SerialName("personal_information") val data: PersonalInformation?
)

@Serializable
data class PersonalInformation(
    val name: String?,
    val title: String?,
    val summary: String?,
    val languages: List<String>?,
    @SerialName("image_url") val image: String?,
)
