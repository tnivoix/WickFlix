package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import toto.isis.fr.models.template.TemplateFull

open class DetailViewModel(private val navController: NavController) : ViewModel()  {

    val detail = MutableStateFlow<TemplateFull?>(null)

    fun moveBack(){
        navController.popBackStack()
    }

    open fun getDetail(id : Int){}
}