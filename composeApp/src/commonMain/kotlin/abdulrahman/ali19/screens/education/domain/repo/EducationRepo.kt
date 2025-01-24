package abdulrahman.ali19.screens.education.domain.repo

import abdulrahman.ali19.screens.education.domain.entity.EducationEntity

interface EducationRepo {
    suspend fun getEducationInformation(): List<EducationEntity>
}