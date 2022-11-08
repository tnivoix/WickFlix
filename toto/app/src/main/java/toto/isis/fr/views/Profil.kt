package toto.isis.fr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import toto.isis.fr.viewModels.ProfilViewModel

@Composable
fun ProfilPicture(size: Dp) {
    Image(
        painterResource(id = R.drawable.john),
        contentDescription = stringResource(id = R.string.name),
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(3.dp, Color.Black, CircleShape)
    )
}

@Composable
fun Name() {
    Text(
        text = stringResource(id = R.string.name),
        style = MaterialTheme.typography.h4
    )
}

@Composable
fun Desc() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.desc1),
        )
        Row {
            Text(
                text = stringResource(id = R.string.desc2) + " ",
            )
            Text(
                text = stringResource(id = R.string.desc3),
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = stringResource(id = R.string.desc4),
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun Stats() {
    Row() {
        Counter(
            text = stringResource(id = R.string.kill),
            number = 77,
            color = Color.Red,
            R.drawable.kill
        )
        Spacer(modifier = Modifier.width(100.dp))
        Counter(
            text = stringResource(id = R.string.money),
            number = 480 * 2000,
            color = Color.Yellow,
            R.drawable.coin
        )
    }
}

@Composable
fun Counter(text: String, number: Int, color: Color, image: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(2.dp, Color.Black)
            .background(Color.LightGray)
            .padding(10.dp, 5.dp)
    ) {
        Text(
            text = text,
            color = color
        )
        Row() {
            Image(
                painterResource(id = image),
                contentDescription = text,
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = number.toString(),
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

@Composable
fun EngageButton(profilViewModel: ProfilViewModel) {
    Button(onClick = { profilViewModel.moveToFilms() }) {
        Text(text = stringResource(id = R.string.button))
    }
}

@Composable
fun ProfilView(windowClass: WindowSizeClass, profilViewModel: ProfilViewModel) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                ProfilPicture(240.dp)
                Name()
                Desc()
                Stats()
                EngageButton(profilViewModel)
            }
        }
        else -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ProfilPicture(200.dp)
                        Spacer(modifier = Modifier.height(15.dp))
                        Name()
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Desc()
                        Spacer(modifier = Modifier.height(25.dp))
                        Counter(
                            text = stringResource(id = R.string.kill),
                            number = 77,
                            color = Color.Red,
                            R.drawable.kill
                        )
                        Spacer(modifier = Modifier.height(35.dp))
                        Counter(
                            text = stringResource(id = R.string.money),
                            number = 480 * 2000,
                            color = Color.Yellow,
                            R.drawable.coin
                        )
                    }
                }
                EngageButton(profilViewModel)
            }

        }
    }
}
