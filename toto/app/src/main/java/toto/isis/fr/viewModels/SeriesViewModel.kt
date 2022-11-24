package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.template.TemplateShort
import toto.isis.fr.models.tmdb.TmdbTvShort

class SeriesViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SeriesViewModel(navController) as T
}

class SeriesViewModel(private val navController: NavController) : ListViewModel(navController) {

    override fun moveToDetail(detailId : Int){
        navController.navigate("serie/"+detailId.toString())
    }

    override fun getList(){
        viewModelScope.launch {
            val seriesResults = Api.service.lastSeries()
            val series = mutableListOf<TemplateShort>()
            for(serie in seriesResults.results){
                val tmplSerie = TemplateShort(serie.id,serie.name,serie.poster_path,serie.first_air_date)
                series.add(tmplSerie)
            }
            list.value = series
        }
    }
}