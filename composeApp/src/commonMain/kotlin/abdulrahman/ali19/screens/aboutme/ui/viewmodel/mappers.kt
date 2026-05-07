package abdulrahman.ali19.screens.aboutme.ui.viewmodel

import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationType
import abdulrahman.ali19.screens.aboutme.domain.model.PersonalInformationEntity
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutTechStackItemState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutValueItemState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.PersonalInformationState

fun PersonalInformationEntity.toPersonalInformationState(): PersonalInformationState {
    return PersonalInformationState(
        name = this.name ?: "",
        title = this.title ?: "",
        summary = this.summary ?: "",
        greeting = this.greeting ?: "",
        quote = this.quote ?: "",
        techStackTitle = this.techStackTitle ?: "",
        techStack = this.techStack?.map {
            AboutTechStackItemState(
                icon = it.icon ?: "",
                label = it.label ?: ""
            )
        } ?: emptyList(),
        valueItems = this.valueItems?.map {
            AboutValueItemState(
                icon = it.icon ?: "",
                title = it.title ?: "",
                description = it.description ?: ""
            )
        } ?: emptyList(),
        languages = this.languages ?: emptyList(),
        image = this.image ?: ""
    )
}

fun List<ContactInformationEntity>.toContactsState(): List<ContactsState> {
    return this.map {
        ContactsState(
            type = it.type ?: ContactInformationType.NON,
            value = it.userName ?: it.value ?: "",
            url = "${it.url}",
            icon = "${it.icon}",
            isCopyable = it.isCopyable ?: false,
            isClickable = it.isClickable ?: false
        )
    }
}
