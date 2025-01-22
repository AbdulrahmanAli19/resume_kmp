package abdulrahman.ali19.screens.aboutme.ui.viewmodel

import abdulrahman.ali19.core.BaseViewModel
import abdulrahman.ali19.screens.aboutme.copyToClipboard
import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationType
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetContactsUsecase
import abdulrahman.ali19.screens.aboutme.domain.usecase.GetPersonalInformationUsecase
import abdulrahman.ali19.screens.aboutme.openDialer
import abdulrahman.ali19.screens.aboutme.openEmailClient
import abdulrahman.ali19.screens.aboutme.openNewWindow
import abdulrahman.ali19.screens.aboutme.showToast
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutEvents
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutScreenState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AboutViewModel(
    private val getPersonalInformationUsecase: GetPersonalInformationUsecase,
    private val getContactsUsecase: GetContactsUsecase
) : BaseViewModel<AboutScreenState, AboutEvents>(initialState = AboutScreenState()) {

    init {
        viewModelScope.launch {
            val personalInformationEntity = getPersonalInformationUsecase()
            _state.update { it.copy(personalInformationState = personalInformationEntity.toState()) }

            val contactsEntity = getContactsUsecase()
            _state.update { it.copy(contacts = contactsEntity.toState()) }
        }
    }

    override fun sendEvent(event: AboutEvents) {
        when (event) {
            is AboutEvents.ContactClickEvent -> handelContactClick(event.item)
            is AboutEvents.CopyClickEvent -> copyUri(event.uri)
        }
    }

    private fun copyUri(uri: String) {
        copyToClipboard(value = uri, showToast = {
            showToast(message = "Copied to clipboard!")
        })
    }

    private fun handelContactClick(item: ContactsState) {
        when (item.type) {
            ContactInformationType.GITHUB, ContactInformationType.LINKEDIN -> openNewWindow(url = item.url)
            ContactInformationType.EMAIL -> openEmailClient(item.url)
            ContactInformationType.PHONE_NUMBER -> openDialer(item.url)
            ContactInformationType.NON, ContactInformationType.ADDRESS -> Unit
        }
    }

}


