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
import toto.isis.fr.viewModels.ActorsViewModel

@Composable
fun ActorsView(windowClass: WindowSizeClass, actorsViewModel: ActorsViewModel) {

    val actors by actorsViewModel.actors.collectAsState()
    actorsViewModel.getActors()
    LazyColumn {
        item {
            Text(text = "Actors")
        }
        item {
            Button(onClick = { actorsViewModel.moveToHome() }) {
                Text(text = "Home")
            }
        }
        items(actors) { actor ->
            Text(text = actor.name)
        }
    }
}