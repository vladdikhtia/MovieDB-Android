package nl.hva.madlevel4task2.ui.screens

import androidx.annotation.StringRes

sealed class MovieScreens(val route: String){
    object OverviewScreen: MovieScreens("overview")
    object DetailScreen: MovieScreens("detail")
}
