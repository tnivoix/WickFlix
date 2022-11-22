package toto.isis.fr

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.compose.AsyncImage
import toto.isis.fr.api.Api
import toto.isis.fr.api.Api.apiImg
import toto.isis.fr.api.Size
import toto.isis.fr.models.TmdbMovieShort
import toto.isis.fr.viewModels.FilmsViewModel

@Composable
fun FilmsView(windowClass: WindowSizeClass, filmsViewModel: FilmsViewModel) {

    val movies by filmsViewModel.movies.collectAsState()
    filmsViewModel.getMovies()
    LazyColumn {
        item {
            Text(text = "Films")
        }
        item {
            Button(onClick = { filmsViewModel.moveToHome() }) {
                Text(text = "Home")
            }
        }
        items(movies) { movie ->
            Button(onClick = { filmsViewModel.moveToFilm(movie.id) }) {
                Text(text = movie.title)
            }
            val moveToFilm: () -> Unit = { filmsViewModel.moveToFilm(movie.id)}
            GridItem(Api.apiImg + Size.w500 + movie.poster_path, movie.title, movie.release_date, moveToFilm)
        }
    }
}

@Composable
fun filmShort(movie : TmdbMovieShort){
    val size = Size.original
    AsyncImage(
        model = apiImg+size+movie.poster_path,
        contentDescription = movie.title
    )
}