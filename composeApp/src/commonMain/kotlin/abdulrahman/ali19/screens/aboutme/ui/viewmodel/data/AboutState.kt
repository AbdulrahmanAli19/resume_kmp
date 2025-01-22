package abdulrahman.ali19.screens.aboutme.ui.viewmodel.data

import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationType

data class AboutScreenState(
    val personalInformationState: PersonalInformationState = PersonalInformationState(),
    val contacts: List<ContactsState> = emptyList()
)

data class ContactsState(
    val type: ContactInformationType = ContactInformationType.NON,
    val value: String = "",
    val url: String = "",
    val icon: String = "",
    val isCopyable: Boolean = false,
    val isClickable: Boolean = false
)

data class PersonalInformationState(
    val name: String = "",
    val title: String = "",
    val summary: String = "",
    val languages: List<String> = emptyList(),
    val image: String = ""
)

sealed class AboutEvents {
    data class ContactClickEvent(val item: ContactsState) : AboutEvents()
    data class CopyClickEvent(val uri: String) : AboutEvents()
}