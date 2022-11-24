package toto.isis.fr.models.tmdb

data class TmdbPersonShort(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<TmdbMovieShort>,
    val known_for_department: String,
    val media_type: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)