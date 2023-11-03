package nl.hva.madlevel4task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.hva.madlevel4task2.ui.screens.DetailScreen
import nl.hva.madlevel4task2.ui.screens.MovieScreens
import nl.hva.madlevel4task2.ui.screens.OverviewScreen
import nl.hva.madlevel4task2.ui.theme.MadLevel4Task2Theme
import nl.hva.madlevel4task2.viewModel.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadLevel4Task2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MovieNavHost(navController, viewModel())
                }
            }
        }
    }
}

@Composable
fun MovieNavHost(navController : NavHostController, viewModel : MoviesViewModel) {
//    val screens = listOf(
//        MovieScreens.OverviewScreen,
//        MovieScreens.DetailScreen
//    )

    NavHost(
        navController = navController,
        startDestination = MovieScreens.OverviewScreen.route
    ){
        composable(route = MovieScreens.OverviewScreen.route){
            OverviewScreen(viewModel = viewModel, navigateToDetailScreen = {
                navController.navigate(MovieScreens.DetailScreen.route)
            })
        }
        composable(route = MovieScreens.DetailScreen.route){
            DetailScreen(navController, viewModel)
        }
    }

}


