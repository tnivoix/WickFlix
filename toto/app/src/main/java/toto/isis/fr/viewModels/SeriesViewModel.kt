package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.TmdbTvShort

class SeriesViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SeriesViewModel(navController) as T
}

class SeriesViewModel(private val navController: NavController) : ViewModel() {

    val series = MutableStateFlow<List<TmdbTvShort>>(listOf())

    fun moveToHome(){
        navController.navigate("profil")
    }

    fun moveToSerie(serieId : Int){
        navController.navigate("serie/"+serieId.toString())
    }

    fun getSeries(){
        viewModelScope.launch {
            val seriesResults = Api.service.lastSeries()
            series.value = seriesResults.results
        }
    }
}