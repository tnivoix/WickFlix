package toto.isis.fr.models.tmdb

data class TmdbTvResult(
    val page: Int,
    val results: List<TmdbTvShort>,
    val total_pages: Int,
    val total_results: Int
)