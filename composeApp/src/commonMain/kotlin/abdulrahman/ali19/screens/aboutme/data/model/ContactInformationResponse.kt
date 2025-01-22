package abdulrahman.ali19.screens.aboutme.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactInformationResponse(
    @SerialName("contacts") val data: List<ContactInformation>?
)

@Serializable
data class ContactInformation(
    val type: String?,
    val value: String?,
    val url: String?,
    @SerialName("user_name") val userName: String?,
    val icon: String?,
    @SerialName("is_copyable") val isCopyable: Boolean?,
    @SerialName("is_clickable") val isClickable: Boolean?
)