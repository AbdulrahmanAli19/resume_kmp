package abdulrahman.ali19.screens.aboutme.domain.usecase

import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

class GetContactsUsecase(
    private val repo: AboutMeRepo
) {
    suspend operator fun invoke(): List<ContactInformationEntity> {
        val result = repo.getContactInformation()
        Napier.log(tag = "ContactInformationEntity", message = result.toString(), priority = LogLevel.INFO)
        return result
    }
}