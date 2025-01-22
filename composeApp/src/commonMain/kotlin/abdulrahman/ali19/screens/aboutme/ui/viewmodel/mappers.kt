package abdulrahman.ali19.screens.aboutme.ui.viewmodel

import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationType
import abdulrahman.ali19.screens.aboutme.domain.model.EducationEntity
import abdulrahman.ali19.screens.aboutme.domain.model.PersonalInformationEntity
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ActivityState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.EducationState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.PersonalInformationState

fun PersonalInformationEntity.toState(): PersonalInformationState {
    return PersonalInformationState(
        name = "${this.name}",
        title = "${this.title}",
        summary = "${this.summary}",
        languages = this.languages ?: emptyList(),
        image = "${this.image}"
    )
}

fun List<ContactInformationEntity>.toState(): List<ContactsState> {
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

fun List<EducationEntity>.toState(): List<EducationState> {
    return this.map {
        EducationState(
            name = it.name,
            startDate = it.startDate,
            endDate = it.endDate,
            location = it.location,
            info = it.info,
            department = it.department,
            activities = it.activities.map { activity ->
                ActivityState(
                    title = activity.title,
                    name = activity.name,
                    startDate = activity.startDate,
                    endDate = activity.endDate
                )
            }
        )

    }
}