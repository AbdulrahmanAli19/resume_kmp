package abdulrahman.ali19.screens.aboutme.domain.usecase

import abdulrahman.ali19.screens.aboutme.domain.model.PersonalInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo

class GetPersonalInformationUseCase(
    private val repo: AboutMeRepo
) {
    suspend operator fun invoke(): PersonalInformationEntity {
        val result = repo.getPersonalInformation()
        return result
    }
}