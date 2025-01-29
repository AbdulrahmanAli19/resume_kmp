package abdulrahman.ali19.screens.experience.data.repo

import abdulrahman.ali19.screens.experience.data.model.ExperienceResponse
import abdulrahman.ali19.screens.experience.data.model.mapper.toEntity
import abdulrahman.ali19.screens.experience.domain.entity.ExperienceEntity
import abdulrahman.ali19.screens.experience.domain.repo.ExperienceRepo
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import resume.composeapp.generated.resources.Res.readBytes

class ExperienceRepoImpl : ExperienceRepo {

    override suspend fun getExperienceInformation(): ExperienceEntity {
        val jsonString = getResource<ExperienceResponse>("experience.json")
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