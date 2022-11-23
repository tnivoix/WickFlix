package toto.isis.fr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import toto.isis.fr.viewModels.SerieViewModel

@Composable
fun SerieView(windowClass: WindowSizeClass, serieViewModel: SerieViewModel, serieId: Int?) {

    val serie by serieViewModel.serie.collectAsState()
    if (serieId != null) {
        serieViewModel.getSerie(serieId)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        serie?.let {
            item(span = { GridItemSpan(2) }) {
                Title1(text = it.name)
            }
            item(span = { GridItemSpan(2) }) {
                Poster(img = it.poster_path, title = it.name)
            }
            item {
                ReleaseDate(releaseDate = it.first_air_date)
            }
            item {
                Genres(genres = it.genres)
            }
            item(span = { GridItemSpan(2) }) {
                BackDrop(img = it.backdrop_path, title = it.name)
            }
            item(span = { GridItemSpan(2) }) {
                Synopsis(synopsis = it.overview)
            }
            item(span = { GridItemSpan(2) }) {
                Title2(text = "Casting")
            }
            items(it.credits.cast) { actor ->
                Casting(img = actor.profile_path, text1 = actor.name, text2 = actor.character)
            }
            item(span = { GridItemSpan(2) }) {
                Button(onClick = { serieViewModel.moveToSeries() }) {
                    Text(text = "Retour")
                }
            }
        }
    }
}