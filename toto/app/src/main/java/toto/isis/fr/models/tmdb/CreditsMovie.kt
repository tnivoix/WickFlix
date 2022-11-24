package toto.isis.fr.models.tmdb

data class CreditsMovie(
    val cast: List<CastMovie>,
    val crew: List<Crew>
)