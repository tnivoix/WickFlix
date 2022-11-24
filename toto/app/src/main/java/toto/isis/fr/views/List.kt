package toto.isis.fr

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import toto.isis.fr.api.Api.apiImg
import toto.isis.fr.api.Size
import toto.isis.fr.viewModels.ListViewModel

@Composable
fun ListView(windowClass: WindowSizeClass, listViewModel: ListViewModel, type : String) {

    val list by listViewModel.list.collectAsState()
    listViewModel.getList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        item {
            TitleList(text = type)
        }
        item {
            ButtonHome(listViewModel = listViewModel)
        }
        items(list) { item ->
            var modifier = gridModifier
            if (type != "Actors")
                modifier = modifier.clickable { listViewModel.moveToDetail(item.id) }

            GridItem(
                img = item.poster_path.toString(),
                text1 = item.name,
                text2 = item.release_date,
                modifier = modifier
            )

        }
    }
}

@Composable
fun TitleList(text: String) {
    Text(text = text)
}

@Composable
fun ButtonHome(listViewModel: ListViewModel) {
    Button(onClick = { listViewModel.moveToHome() }) {
        Text(text = "Home")
    }
}

@Composable
fun GridItem(img: String = "", text1: String, text2: String = "", modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        val padd = if (text2 == "")
            5.dp
        else
            0.dp
        if (img != "") {
            AsyncImage(
                model = apiImg + Size.w500 + img,
                contentDescription = text1,
                modifier = Modifier.padding(10.dp)
            )
        }
        Text(
            text = text1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, padd)
        )
        if (text2 != "") {
            Text(
                text = text2,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(10.dp, 5.dp)
            )
        }
    }
}

