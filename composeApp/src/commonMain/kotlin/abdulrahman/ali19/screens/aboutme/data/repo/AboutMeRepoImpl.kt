package abdulrahman.ali19.screens.aboutme.data.repo

import abdulrahman.ali19.screens.aboutme.data.model.ContactInformationResponse
import abdulrahman.ali19.screens.aboutme.data.model.PersonalInformationResponse
import abdulrahman.ali19.screens.aboutme.data.model.mappers.toEntity
import abdulrahman.ali19.screens.aboutme.domain.model.ContactInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.model.PersonalInformationEntity
import abdulrahman.ali19.screens.aboutme.domain.repo.AboutMeRepo
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import resume.composeapp.generated.resources.Res.readBytes


class AboutMeRepoImpl : AboutMeRepo {

    override suspend fun getPersonalInformation(): PersonalInformationEntity {
        val jsonString = getResource<PersonalInformationResponse>("personal_information.json")
        return jsonString.toEntity()
    }

    override suspend fun getContactInformation(): List<ContactInformationEntity> {
        val jsonString = getResource<ContactInformationResponse>("contacts.json")
        return jsonString.toEntity()
    }


    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }


    @OptIn(ExperimentalResourceApi::class)
    private suspend inline fun <reified T> getResource(path: String): T {
        val response = readBytes("files/webview/$path").decodeToString()
        val result = json.decodeFromString<T>(response)
        return result
    }

}