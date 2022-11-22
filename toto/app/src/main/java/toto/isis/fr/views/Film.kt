package toto.isis.fr

import android.widget.GridLayout
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import toto.isis.fr.api.Api
import toto.isis.fr.api.Size
import toto.isis.fr.models.CastMovie
import toto.isis.fr.viewModels.FilmViewModel

@Composable
fun FilmView(windowClass: WindowSizeClass, filmViewModel: FilmViewModel, filmId: Int?) {

    val film by filmViewModel.film.collectAsState()
    if (filmId != null) {
        filmViewModel.getFilm(filmId)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        //horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        film?.let {
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = it.title,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
                )
            }
            item(span = { GridItemSpan(2) }) {
                AsyncImage(
                    model = Api.apiImg + Size.w780 + it.poster_path,
                    contentDescription = it.title,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
                )
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Date de sortie",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = it.release_date,
                        color = Color.LightGray
                    )
                }
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Genres",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                    for (genre in it.genres) {
                        Text(
                            text = genre.name,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
            item(span = { GridItemSpan(2) }) {
                AsyncImage(
                    model = Api.apiImg + Size.original + it.backdrop_path,
                    contentDescription = it.title,
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
            }
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Synopsis",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp),
                    textAlign = TextAlign.Start
                )
            }
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = it.overview,
                    textAlign = TextAlign.Justify
                )
            }
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Casting",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp),
                    textAlign = TextAlign.Start
                )
            }
            items(it.credits.cast) { actor ->
                //GridItem(Api.apiImg + Size.w500 + actor.profile_path, actor.name, actor.character)
            }
            item(span = { GridItemSpan(2) }) {
                Button(onClick = { filmViewModel.moveToFilms() }) {
                    Text(text = "Retour")
                }
            }
        }
    }
}
