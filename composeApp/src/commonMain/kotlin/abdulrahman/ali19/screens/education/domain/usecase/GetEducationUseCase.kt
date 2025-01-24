package abdulrahman.ali19.screens.education.domain.usecase

import abdulrahman.ali19.screens.education.domain.entity.EducationEntity
import abdulrahman.ali19.screens.education.domain.repo.EducationRepo

class GetEducationUseCase(
    private val repo: EducationRepo
) {
    suspend operator fun invoke(): List<EducationEntity> {
        val result = repo.getEducationInformation()
        return result
    }
}