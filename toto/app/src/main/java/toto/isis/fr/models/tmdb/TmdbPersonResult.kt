package toto.isis.fr.models.tmdb

data class TmdbPersonResult(
    val page: Int,
    val results: List<TmdbPersonShort>,
    val total_pages: Int,
    val total_results: Int
)