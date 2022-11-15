package toto.isis.fr

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import toto.isis.fr.api.Api
import toto.isis.fr.api.Size
import toto.isis.fr.viewModels.FilmViewModel

@Composable
fun FilmView(windowClass: WindowSizeClass, filmViewModel: FilmViewModel, filmId: Int?) {

    val film by filmViewModel.film.collectAsState()
    if (filmId != null) {
        filmViewModel.getFilm(filmId)
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        film?.let {
            item {
                Text(
                    text = it.title,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
            }
            item {
                AsyncImage(
                    model = Api.apiImg + Size.w780 + it.poster_path,
                    contentDescription = it.title
                )
            }
            item {
                AsyncImage(
                    model = Api.apiImg + Size.original + it.backdrop_path,
                    contentDescription = it.title
                )
            }
            item {
                Button(onClick = { filmViewModel.moveToFilms() }) {
                    Text(text = "Retour")
                }
            }
        }
    }
}