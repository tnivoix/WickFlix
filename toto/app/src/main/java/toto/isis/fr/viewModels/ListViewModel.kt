package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import toto.isis.fr.models.template.TemplateFull
import toto.isis.fr.models.template.TemplateShort
import toto.isis.fr.models.tmdb.TmdbMovieShort

open class ListViewModel(private val navController: NavController) : ViewModel()  {

    val list = MutableStateFlow<List<TemplateShort>>(listOf())

    fun moveToHome(){
        navController.navigate("profil")
    }

    open fun moveToDetail(detailId : Int){}

    open fun getList(){}
}