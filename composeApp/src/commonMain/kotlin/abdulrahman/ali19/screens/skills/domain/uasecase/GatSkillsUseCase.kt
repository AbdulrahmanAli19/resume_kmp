package abdulrahman.ali19.screens.skills.domain.uasecase

import abdulrahman.ali19.screens.skills.domain.entity.SkillsEntity
import abdulrahman.ali19.screens.skills.domain.repo.SkillsRepo

class GatSkillsUseCase(
    private val repo: SkillsRepo
) {
    suspend operator fun invoke(): SkillsEntity {
        val result = repo.getSkills()
        return result
    }
}