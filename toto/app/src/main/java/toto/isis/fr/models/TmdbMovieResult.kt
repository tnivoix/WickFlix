package toto.isis.fr.models

data class TmdbMovieResult(
    val page: Int,
    val results: List<TmdbMovieShort>,
    val total_pages: Int,
    val total_results: Int
)