package toto.isis.fr.models

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int
)