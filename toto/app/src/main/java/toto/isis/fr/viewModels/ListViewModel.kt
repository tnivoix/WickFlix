package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import toto.isis.fr.models.template.TemplateShort

open class ListViewModel(private val navController: NavController) : MyViewModel(navController) {

    val list = MutableStateFlow<List<TemplateShort>>(listOf())

    override fun moveBack() {
        navController.navigate("profil")
    }

    open fun search(search: String) {}
    open fun moveToDetail(detailId: Int) {}

    open fun getList() {}
}