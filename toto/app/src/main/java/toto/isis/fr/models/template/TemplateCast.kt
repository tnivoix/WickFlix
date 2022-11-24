package toto.isis.fr.models.template

data class TemplateCast(
    val id: Int,
    val name: String,
    val character: String,
    val gender: Int,
    val profile_path: String?
)
