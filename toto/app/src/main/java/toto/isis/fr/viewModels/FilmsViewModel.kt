package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.Api
import toto.isis.fr.models.TmdbMovieShort

class FilmsViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FilmsViewModel(navController) as T
}

class FilmsViewModel(private val navController: NavController) : ViewModel() {

    val movies = MutableStateFlow<List<TmdbMovieShort>>(listOf())

    fun moveToHome(){
        navController.navigate("profil")
    }

    fun moveToFilm(filmId : Int){
        navController.navigate("film/"+filmId.toString())
    }

    fun getMovies(){
        viewModelScope.launch {
            val moviesResults = Api.service.lastMovies()
            movies.value = moviesResults.results
        }
    }
}