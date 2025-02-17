package abdulrahman.ali19.screens.education.domain.usecase

import abdulrahman.ali19.screens.education.domain.entity.CourseEntity
import abdulrahman.ali19.screens.education.domain.repo.EducationRepo

class GetCoursesUseCase(
    private val repo: EducationRepo
) {
    suspend operator fun invoke(): List<CourseEntity> {
        val result = repo.getCoursesInformation()
        return result
    }
}