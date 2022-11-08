package toto.isis.fr.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

class ProfilViewModelFactory(private val navController: NavController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProfilViewModel(navController) as T
}

class ProfilViewModel(private val navController: NavController) : ViewModel() {

    fun moveToFilms(){
        navController.navigate("films")
    }
}