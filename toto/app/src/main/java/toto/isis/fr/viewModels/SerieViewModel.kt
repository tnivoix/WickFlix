package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.template.TemplateCast
import toto.isis.fr.models.template.TemplateFull

class SerieViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SerieViewModel(navController) as T
}

class SerieViewModel(private val navController: NavController) : DetailViewModel(navController) {

    override fun getDetail(id : Int){
        viewModelScope.launch {
            val serie = Api.service.serie(id = id)
            val casting = mutableListOf<TemplateCast>()
            for(cast in serie.credits.cast){
                val tmplCast = TemplateCast(cast.id,cast.name,cast.character,cast.gender,cast.profile_path)
                casting.add(tmplCast)
            }
            detail.value = TemplateFull(
                serie.id,
                serie.name,
                serie.genres,
                casting,
                serie.overview,
                serie.poster_path,
                serie.backdrop_path,
                serie.first_air_date
            )
        }
    }
}