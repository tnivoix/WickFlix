package toto.isis.fr

sealed class NavItem(var title:String, var icon:Int, var screen_route:String){

    object Films : NavItem("Films", R.drawable.films,"films")
    object Series: NavItem("Series",R.drawable.series,"series")
    object Actors: NavItem("Actors",R.drawable.actors,"actors")
}