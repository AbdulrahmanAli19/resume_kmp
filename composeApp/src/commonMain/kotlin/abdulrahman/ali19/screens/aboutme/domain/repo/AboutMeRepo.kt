package abdulrahman.ali19.screens.aboutme.domain.repo

import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.model.PersonalInformationEntity

interface AboutMeRepo {
    suspend fun getPersonalInformation(): PersonalInformationEntity
    suspend fun getContactInformation(): List<ContactInformationEntity>
}