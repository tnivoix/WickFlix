package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.TmdbMovieFull

class FilmViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FilmViewModel(navController) as T
}

class FilmViewModel(private val navController: NavController) : ViewModel() {

    val film = MutableStateFlow<TmdbMovieFull?>(null)

    fun moveToFilms(){
        navController.navigate("films")
    }

    fun getFilm(id : Int){
        viewModelScope.launch {
            film.value = Api.service.movie(id = id)
        }
    }
}