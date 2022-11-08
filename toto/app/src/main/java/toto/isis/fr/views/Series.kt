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
import toto.isis.fr.viewModels.SeriesViewModel

@Composable
fun SeriesView(windowClass: WindowSizeClass, seriesViewModel: SeriesViewModel){
    val series by seriesViewModel.series.collectAsState()
    seriesViewModel.getSeries()

    LazyColumn {
        item {
            Text(text = "Series")
        }
        item {
            Button(onClick = { seriesViewModel.moveToHome() }) {
                Text(text = "Home")
            }
        }
        items(series) { serie ->
            Button(onClick = { seriesViewModel.moveToSerie(serie.id) }) {
                Text(text = serie.name)
            }
        }
    }
}