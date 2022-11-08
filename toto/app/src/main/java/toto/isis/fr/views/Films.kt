package toto.isis.fr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
        }
    }
}