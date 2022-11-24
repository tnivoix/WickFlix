package toto.isis.fr

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import toto.isis.fr.api.Api
import toto.isis.fr.api.Size
import toto.isis.fr.models.tmdb.Genre
import toto.isis.fr.viewModels.DetailViewModel

@Composable
fun DetailView(windowClass: WindowSizeClass, detailViewModel: DetailViewModel, detailId: Int?) {

    val detail by detailViewModel.detail.collectAsState()
    if (detailId != null) {
        detailViewModel.getDetail(detailId)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        detail?.let {
            item(span = { GridItemSpan(2) }) {
                Title1(text = it.name)
            }
            item(span = { GridItemSpan(2) }) {
                Poster(img = it.poster_path, title = it.name)
            }
            item {
                ReleaseDate(releaseDate = it.release_date)
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
            items(it.casting) { actor ->
                GridItem(
                    img = actor.profile_path.toString(),
                    text1 = actor.name,
                    text2 = actor.character,
                    gridModifier
                )
            }
            item(span = { GridItemSpan(2) }) {
                BackButton(detailViewModel)
            }
        }
    }
}

@Composable
fun Title1(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
fun Title2(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 5.dp),
        textAlign = TextAlign.Start
    )
}

@Composable
fun Poster(img: String, title: String) {
    AsyncImage(
        model = Api.apiImg + Size.w780 + img,
        contentDescription = title,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
fun ReleaseDate(releaseDate: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Date de sortie",
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = releaseDate,
            color = Color.LightGray
        )
    }
}

@Composable
fun Genres(genres: List<Genre>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Genres",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        for (genre in genres) {
            Text(
                text = genre.name,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun BackDrop(img: String, title: String) {
    AsyncImage(
        model = Api.apiImg + Size.original + img,
        contentDescription = title,
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(5.dp))
    )
}

@Composable
fun Synopsis(synopsis: String) {
    Column() {
        Title2(text = "Synopsis")
        Text(
            text = synopsis,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun BackButton(viewModel: DetailViewModel) {
    Button(onClick = { viewModel.moveBack() }) {
        Text(text = "Retour")
    }
}