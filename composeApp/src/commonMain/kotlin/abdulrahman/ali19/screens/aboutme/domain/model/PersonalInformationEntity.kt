package abdulrahman.ali19.screens.aboutme.domain.model

data class PersonalInformationEntity(
    val name: String?,
    val title: String?,
    val summary: String?,
    val greeting: String?,
    val quote: String?,
    val techStackTitle: String?,
    val techStack: List<AboutTechStackItemEntity>?,
    val valueItems: List<AboutValueItemEntity>?,
    val languages: List<String>?,
    val image: String?
)

data class AboutTechStackItemEntity(
    val icon: String?,
    val label: String?
)

data class AboutValueItemEntity(
    val icon: String?,
    val title: String?,
    val description: String?
)
