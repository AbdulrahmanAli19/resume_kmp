package abdulrahman.ali19.screens.aboutme.data.model.mappers

import abdulrahman.ali19.screens.aboutme.data.model.ContactInformationResponse
import abdulrahman.ali19.screens.aboutme.data.model.PersonalInformationResponse
import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationType
import abdulrahman.ali19.screens.aboutme.domain.model.PersonalInformationEntity

fun PersonalInformationResponse.toEntity() =
    PersonalInformationEntity(
        name = data?.name,
        title = data?.title,
        summary = data?.summary,
        languages = data?.languages,
        image = data?.image
    )

fun ContactInformationResponse.toEntity() = data?.map {
    ContactInformationEntity(
        value = it.value,
        url = it.url,
        userName = it.userName,
        icon = it.icon,
        isCopyable = it.isCopyable,
        isClickable = it.isClickable,
        type = when (it.type) {
            "address" -> ContactInformationType.ADDRESS
            "email" -> ContactInformationType.EMAIL
            "phone_number" -> ContactInformationType.PHONE_NUMBER
            "linkedin" -> ContactInformationType.LINKEDIN
            "github" -> ContactInformationType.GITHUB
            else -> ContactInformationType.NON
        }

    )
} ?: emptyList()