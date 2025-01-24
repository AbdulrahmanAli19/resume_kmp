package abdulrahman.ali19.screens.education.data.repo

import abdulrahman.ali19.screens.education.data.model.EducationResponse
import abdulrahman.ali19.screens.education.data.model.mappers.toEntity
import abdulrahman.ali19.screens.education.domain.entity.EducationEntity
import abdulrahman.ali19.screens.education.domain.repo.EducationRepo
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import resume.composeapp.generated.resources.Res.readBytes

class EducationRepoImpl: EducationRepo {
    override suspend fun getEducationInformation(): List<EducationEntity> {
        val jsonString = getResource<EducationResponse>("education.json")
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