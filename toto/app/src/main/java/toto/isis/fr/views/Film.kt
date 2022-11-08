package toto.isis.fr

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import toto.isis.fr.viewModels.FilmViewModel

@Composable
fun FilmView(windowClass: WindowSizeClass, filmViewModel: FilmViewModel, filmId: Int?) {

    val film by filmViewModel.film.collectAsState()
    if (filmId != null) {
        filmViewModel.getFilm(filmId)
    }

    LazyColumn {
        item {
            film?.let {
                Text(
                    text = it.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = dpToSp(30.dp)
                )
            }
        }
        item {
            Button(onClick = { filmViewModel.moveToFilms() }) {
                Text(text = "Retour")
            }
        }
    }
}