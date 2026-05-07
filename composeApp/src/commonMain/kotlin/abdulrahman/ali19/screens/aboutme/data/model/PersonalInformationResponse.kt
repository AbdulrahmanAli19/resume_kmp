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
    val greeting: String?,
    val quote: String?,
    @SerialName("tech_stack_title") val techStackTitle: String?,
    @SerialName("tech_stack") val techStack: List<AboutTechStackItem>?,
    @SerialName("value_items") val valueItems: List<AboutValueItem>?,
    val languages: List<String>?,
    @SerialName("image_url") val image: String?,
)

@Serializable
data class AboutTechStackItem(
    val icon: String?,
    val label: String?
)

@Serializable
data class AboutValueItem(
    val icon: String?,
    val title: String?,
    val description: String?
)
