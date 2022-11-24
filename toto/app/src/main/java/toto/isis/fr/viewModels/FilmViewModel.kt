package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.template.TemplateCast
import toto.isis.fr.models.template.TemplateFull

class FilmViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FilmViewModel(navController) as T
}

class FilmViewModel(private val navController: NavController) : DetailViewModel(navController) {

    override fun getDetail(id: Int) {
        viewModelScope.launch {
            val film = Api.service.movie(id = id)
            val casting = mutableListOf<TemplateCast>()
            for(cast in film.credits.cast){
                val tmplCast = TemplateCast(cast.id,cast.name,cast.character,cast.gender,cast.profile_path)
                casting.add(tmplCast)
            }
            detail.value = TemplateFull(
                film.id,
                film.title,
                film.genres,
                casting,
                film.overview,
                film.poster_path,
                film.backdrop_path,
                film.release_date
            )
        }
    }
}