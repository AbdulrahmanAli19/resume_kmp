package abdulrahman.ali19.screens.skills.domain.repo

import abdulrahman.ali19.screens.skills.domain.entity.SkillsEntity

interface SkillsRepo {
    suspend fun getSkills(): SkillsEntity
}