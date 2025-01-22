package abdulrahman.ali19.screens.aboutme.domain.model

data class ContactInformationEntity(
    val type: ContactInformationType?,
    val value: String?,
    val url: String?,
    val userName: String?,
    val icon: String?,
    val isCopyable: Boolean?,
    val isClickable: Boolean?
)

enum class ContactInformationType {
    ADDRESS,
    EMAIL,
    PHONE_NUMBER,
    LINKEDIN,
    GITHUB,
    NON
}