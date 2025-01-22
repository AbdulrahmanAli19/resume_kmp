package abdulrahman.ali19.screens.aboutme.domain.usecase

import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo

class GetContactsUseCase(
    private val repo: AboutMeRepo
) {
    suspend operator fun invoke(): List<ContactInformationEntity> {
        val result = repo.getContactInformation()
        return result
    }
}