package abdulrahman.ali19.screens.experience.domain.repo

import abdulrahman.ali19.screens.experience.domain.entity.ExperienceEntity

interface ExperienceRepo {
    suspend fun getExperienceInformation(): ExperienceEntity
}