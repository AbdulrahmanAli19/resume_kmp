package abdulrahman.ali19.screens.aboutme.ui.viewmodel

import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationType
import abdulrahman.ali19.screens.aboutme.domain.model.PersonalInformationEntity
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.PersonalInformationState
import org.jetbrains.compose.resources.DrawableResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.call
import resume.composeapp.generated.resources.email
import resume.composeapp.generated.resources.github
import resume.composeapp.generated.resources.linkedin
import resume.composeapp.generated.resources.location

fun PersonalInformationEntity.toPersonalInformationState(): PersonalInformationState {
    return PersonalInformationState(
        name = "${this.name}",
        title = "${this.title}",
        summary = "${this.summary}",
        languages = this.languages ?: emptyList(),
        image = "${this.image}"
    )
}

fun List<ContactInformationEntity>.toContactsState(): List<ContactsState> {
    return this.map {
        ContactsState(
            type = it.type ?: ContactInformationType.NON,
            value = it.userName ?: it.value ?: "",
            url = "${it.url}",
            icon = getContactIcon(it.icon),
            isCopyable = it.isCopyable ?: false,
            isClickable = it.isClickable ?: false
        )
    }
}

private fun getContactIcon(icon: String?): DrawableResource? {
    return when (icon) {
        "drawable/location.xml" -> Res.drawable.location
        "drawable/email.xml" -> Res.drawable.email
        "drawable/call.xml" -> Res.drawable.call
        "drawable/linkedin.xml" -> Res.drawable.linkedin
        "drawable/github.xml" -> Res.drawable.github
        else -> null
    }
}
