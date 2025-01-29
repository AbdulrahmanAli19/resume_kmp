package abdulrahman.ali19.screens.experience.domain.uasecase

import abdulrahman.ali19.screens.experience.domain.entity.ExperienceEntity
import abdulrahman.ali19.screens.experience.domain.repo.ExperienceRepo

class GatExperienceUseCase(
    private val repo: ExperienceRepo
) {
    suspend operator fun invoke(): ExperienceEntity {
        val result = repo.getExperienceInformation()
        return result
    }
}