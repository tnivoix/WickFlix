package toto.isis.fr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
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
            var showBars by rememberSaveable { mutableStateOf(true) }
            var showSearch by rememberSaveable { mutableStateOf(true) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
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
            val viewModels = mapOf(
                "profil" to profilViewModel,
                "films" to filmsViewModel,
                "series" to seriesViewModel,
                "actors" to actorsViewModel,
                "film" to filmViewModel,
                "serie" to serieViewModel
            )

            showBars = when (navBackStackEntry?.destination?.route) {
                "profil" -> false // on this screen bottom bar should be hidden
                else -> true // in all other cases show bottom bar
            }

            showSearch = when (navBackStackEntry?.destination?.route) {
                "films" -> true // on this screen bottom bar should be hidden
                "series" -> true
                "actors" -> true
                else -> false // in all other cases show bottom bar
            }

            Scaffold(
                bottomBar = {
                    if (showBars) {
                        BottomNavigation(navController = navController)
                    }
                },
                topBar = {
                    if (showBars) {
                        TopSearch(navController = navController, viewModels, showSearch)
                    }
                }
            ) { paddingValues ->
                NavGraph(windowSizeClass, navController, paddingValues, viewModels)
            }
        }
    }

    @Composable
    fun NavGraph(
        windowSizeClass: WindowSizeClass,
        navController: NavHostController,
        paddingValues: PaddingValues,
        viewModels: Map<String, ViewModel>
    ) {


        NavHost(
            navController = navController,
            startDestination = "profil",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("profil") {
                ProfilView(windowSizeClass, viewModels.get("profil") as ProfilViewModel)
            }
            composable("films") {
                ListView(windowSizeClass, viewModels.get("films") as ListViewModel, "Films")
            }
            composable("series") {
                ListView(windowSizeClass, viewModels.get("series") as ListViewModel, "Series")
            }
            composable("actors") {
                ListView(windowSizeClass, viewModels.get("actors") as ListViewModel, "Actors")
            }
            composable(
                "film/{filmId}",
                arguments = listOf(navArgument("filmId") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailView(
                    windowSizeClass,
                    viewModels.get("film") as DetailViewModel,
                    backStackEntry.arguments?.getInt("filmId")
                )
            }
            composable(
                "serie/{serieId}",
                arguments = listOf(navArgument("serieId") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailView(
                    windowSizeClass,
                    viewModels.get("serie") as DetailViewModel,
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
            contentColor = Color.Black,
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

        @Composable
        fun SideNavigation(navController: NavController){
            Navigation
        }
    }

    @Composable
    fun TopSearch(
        navController: NavController,
        viewModels: Map<String, ViewModel>,
        showSearch: Boolean
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var currentRoute = navBackStackEntry?.destination?.route?.split("/")?.get(0)
        if (currentRoute == null)
            currentRoute = "films"
        println(currentRoute)
        var myViewModel = viewModels.get(currentRoute) as MyViewModel

        var textState = remember { mutableStateOf<String>("") }
        //textState.value = currentRoute.substring(0, 1).uppercase() + currentRoute.substring(1)

        TopAppBar(
            title = {
                if (showSearch) {
                    TextField(
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        singleLine = true,
                        label = {
                            Text(
                                text = currentRoute.substring(0, 1)
                                    .uppercase() + currentRoute.substring(1)
                            )
                        },
                        placeholder = { Text(text = "Your search") }
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    myViewModel.moveBack()
                }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            actions = {
                if (showSearch and (textState.value != "")) {
                    IconButton(onClick = {
                        (myViewModel as ListViewModel).search(textState.value)
                    }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "search")
                    }
                }
            }
        )
    }
}

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun h1(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = dpToSp(30.dp)
    )
}

@Composable
fun h2(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = dpToSp(20.dp)
    )
}

sealed class NavItem(var title: String, var icon: Int, var screen_route: String) {

    object Films : NavItem("Films", R.drawable.films, "films")
    object Series : NavItem("Series", R.drawable.series, "series")
    object Actors : NavItem("Actors", R.drawable.actors, "actors")
}

val gridModifier = Modifier
    .padding(5.dp)
    .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))