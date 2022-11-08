package toto.isis.fr.models

data class TmdbPersonResult(
    val page: Int,
    val results: List<TmdbPersonShort>,
    val total_pages: Int,
    val total_results: Int
)