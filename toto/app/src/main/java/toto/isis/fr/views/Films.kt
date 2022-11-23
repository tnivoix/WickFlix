package toto.isis.fr

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import toto.isis.fr.api.Api.apiImg
import toto.isis.fr.api.Size
import toto.isis.fr.viewModels.FilmsViewModel

@Composable
fun FilmsView(windowClass: WindowSizeClass, filmsViewModel: FilmsViewModel) {

    val movies by filmsViewModel.movies.collectAsState()
    filmsViewModel.getMovies()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        item {
            Text(text = "Films")
        }
        item {
            Button(onClick = { filmsViewModel.moveToHome() }) {
                Text(text = "Home")
            }
        }
        items(movies) { movie ->
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                    .clickable { filmsViewModel.moveToFilm(movie.id) }
            ) {
                GridItem(
                    img = apiImg + Size.w500 + movie.poster_path,
                    text1 = movie.title,
                    text2 = movie.release_date
                )
            }
        }
    }
}