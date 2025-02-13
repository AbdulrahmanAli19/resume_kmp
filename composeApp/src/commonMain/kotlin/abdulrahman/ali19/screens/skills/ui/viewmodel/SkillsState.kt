package abdulrahman.ali19.screens.skills.ui.viewmodel

data class SkillsState(
    val technicalSkills: List<SkillsItemState> = emptyList(),
    val softwareEngineering: List<String> = emptyList(),
    val softSkills: List<String> = emptyList(),
)

data class SkillsItemState(
    val type: String = "",
    val skills: List<String> = emptyList(),
)

sealed class SkillsEvents {

}