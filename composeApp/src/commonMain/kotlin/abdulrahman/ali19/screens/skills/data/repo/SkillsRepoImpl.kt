package abdulrahman.ali19.screens.skills.data.repo

import abdulrahman.ali19.screens.skills.data.model.SkillsResponse
import abdulrahman.ali19.screens.skills.data.model.mapper.toEntity
import abdulrahman.ali19.screens.skills.domain.entity.SkillsEntity
import abdulrahman.ali19.screens.skills.domain.repo.SkillsRepo
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import resume.composeapp.generated.resources.Res.readBytes

class SkillsRepoImpl : SkillsRepo {
    override suspend fun getSkills(): SkillsEntity {
        val jsonString = getResource<SkillsResponse>("skills.json")
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