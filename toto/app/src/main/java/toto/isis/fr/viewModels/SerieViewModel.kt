package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.TmdbTvFull

class SerieViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SerieViewModel(navController) as T
}

class SerieViewModel(private val navController: NavController) : ViewModel() {

    val serie = MutableStateFlow<TmdbTvFull?>(null)

    fun moveToSeries(){
        navController.navigate("series")
    }

    fun getSerie(id : Int){
        viewModelScope.launch {
            serie.value = Api.service.serie(id = id)
        }
    }
}