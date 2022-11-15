package toto.isis.fr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import toto.isis.fr.viewModels.*


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            showBottomBar = when (navBackStackEntry?.destination?.route) {
                "profil" -> false // on this screen bottom bar should be hidden
                else -> true // in all other cases show bottom bar
            }

            Scaffold(
                bottomBar = {
                    if (showBottomBar) {
                        BottomNavigation(navController = navController)
                    }
                }

            ) { paddingValues ->
                NavGraph(windowSizeClass, navController, paddingValues)
            }
        }
    }

    @Composable
    fun NavGraph(
        windowSizeClass: WindowSizeClass,
        navController: NavHostController,
        paddingValues: PaddingValues
    ) {
        val profilViewModel: ProfilViewModel =
            viewModel(factory = ProfilViewModelFactory(navController))
        val filmsViewModel: FilmsViewModel =
            viewModel(factory = FilmsViewModelFactory(navController))
        val seriesViewModel: SeriesViewModel =
            viewModel(factory = SeriesViewModelFactory(navController))
        val actorsViewModel: ActorsViewModel =
            viewModel(factory = ActorsViewModelFactory(navController))
        val filmViewModel: FilmViewModel =
            viewModel(factory = FilmViewModelFactory(navController))
        val serieViewModel: SerieViewModel =
            viewModel(factory = SerieViewModelFactory(navController))

        NavHost(
            navController = navController,
            startDestination = "profil",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("profil") {
                ProfilView(windowSizeClass, profilViewModel)
            }
            composable("films") {
                FilmsView(windowSizeClass, filmsViewModel)
            }
            composable("series") {
                SeriesView(windowSizeClass, seriesViewModel)
            }
            composable("actors") {
                ActorsView(windowSizeClass, actorsViewModel)
            }
            composable(
                "film/{filmId}",
                arguments = listOf(navArgument("filmId") { type = NavType.IntType })
            ) { backStackEntry ->
                FilmView(
                    windowSizeClass,
                    filmViewModel,
                    backStackEntry.arguments?.getInt("filmId")
                )
            }
            composable(
                "serie/{serieId}",
                arguments = listOf(navArgument("serieId") { type = NavType.IntType })
            ) { backStackEntry ->
                SerieView(
                    windowSizeClass,
                    serieViewModel,
                    backStackEntry.arguments?.getInt("serieId")
                )
            }
        }
    }

    @Composable
    fun BottomNavigation(navController: NavController) {
        val items = listOf(
            NavItem.Films,
            NavItem.Series,
            NavItem.Actors
        )
        BottomNavigation(
            backgroundColor = Color.Yellow,
            contentColor = Color.Black
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text(text = item.title)
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun h1(text : String){
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = dpToSp(30.dp)
    )
}

@Composable
fun h2(text : String){
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = dpToSp(20.dp)
    )
}

sealed class NavItem(var title:String, var icon:Int, var screen_route:String){

    object Films : NavItem("Films", R.drawable.films,"films")
    object Series: NavItem("Series",R.drawable.series,"series")
    object Actors: NavItem("Actors",R.drawable.actors,"actors")
}