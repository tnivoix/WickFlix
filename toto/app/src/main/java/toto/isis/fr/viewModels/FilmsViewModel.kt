package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.template.TemplateCast
import toto.isis.fr.models.template.TemplateShort
import toto.isis.fr.models.tmdb.TmdbMovieShort

class FilmsViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FilmsViewModel(navController) as T
}

class FilmsViewModel(private val navController: NavController) : ListViewModel(navController) {

    override fun moveToDetail(detailId : Int){
        navController.navigate("film/"+detailId.toString())
    }

    override fun getList(){
        viewModelScope.launch {
            val moviesResults = Api.service.lastMovies()
            val films = mutableListOf<TemplateShort>()
            for(film in moviesResults.results){
                val tmplFilm = TemplateShort(film.id,film.title,film.poster_path,film.release_date)
                films.add(tmplFilm)
            }
            list.value = films
        }
    }
}