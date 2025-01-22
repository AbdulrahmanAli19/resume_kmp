package abdulrahman.ali19.screens.aboutme.domain.usecase

import abdulrahman.ali19.screens.aboutme.domain.model.EducationEntity
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo

class GetEducationUseCase(
    private val repo: AboutMeRepo
) {
    suspend operator fun invoke(): List<EducationEntity> {
        val result = repo.getEducationInformation()
        return result
    }
}