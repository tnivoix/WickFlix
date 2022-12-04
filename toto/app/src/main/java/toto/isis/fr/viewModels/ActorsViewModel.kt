package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import toto.isis.fr.api.Api
import toto.isis.fr.models.template.TemplateShort
import toto.isis.fr.models.tmdb.TmdbPersonResult
import toto.isis.fr.models.tmdb.TmdbPersonShort

class ActorsViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ActorsViewModel(navController) as T
}

class ActorsViewModel(private val navController: NavController) : ListViewModel(navController) {

    override fun getList(){
        viewModelScope.launch {
            val actorsResults = Api.service.lastActors()
            toApiToApp(actorsResults)
        }
    }

    override fun search(search: String) {
        viewModelScope.launch {
            val actorsResults = Api.service.searchActors(query = search)
            toApiToApp(actorsResults)
        }
    }

    fun toApiToApp(result: TmdbPersonResult){
        val actors = mutableListOf<TemplateShort>()
        for(actor in result.results){

            val tmplActor = TemplateShort(actor.id,actor.name,actor.profile_path,"")
            actors.add(tmplActor)
        }
        list.value = actors
    }
}