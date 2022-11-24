package toto.isis.fr.models.template

import toto.isis.fr.models.tmdb.Genre

data class TemplateFull(
    val id: Int,
    val name: String,
    val genres: List<Genre>,
    val casting: List<TemplateCast>,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val release_date: String,
)