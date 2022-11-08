package toto.isis.fr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import toto.isis.fr.viewModels.SerieViewModel

@Composable
fun SerieView(windowClass: WindowSizeClass, serieViewModel: SerieViewModel, serieId: Int?) {

    val serie by serieViewModel.serie.collectAsState()
    if (serieId != null) {
        serieViewModel.getSerie(serieId)
    }

    LazyColumn {
        item {
            serie?.let { Text(text = it.name) }
        }
        item {
            Button(onClick = { serieViewModel.moveToSeries() }) {
                Text(text = "Retour")
            }
        }
    }
}