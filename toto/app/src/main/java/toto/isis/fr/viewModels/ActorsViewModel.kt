package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.TmdbPersonShort

class ActorsViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ActorsViewModel(navController) as T
}

class ActorsViewModel(private val navController: NavController) : ViewModel() {

    val actors = MutableStateFlow<List<TmdbPersonShort>>(listOf())

    fun moveToHome(){
        navController.navigate("profil")
    }

    fun getActors(){
        viewModelScope.launch {
            val actorsResults = Api.service.lastActors()
            actors.value = actorsResults.results
        }
    }
}