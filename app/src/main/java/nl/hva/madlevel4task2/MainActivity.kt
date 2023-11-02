package nl.hva.madlevel4task2

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nl.hva.madlevel4task2.ui.screens.MovieScreens
import nl.hva.madlevel4task2.ui.screens.OverviewScreen
import nl.hva.madlevel4task2.ui.theme.MadLevel4Task2Theme

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
                    MovieNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun MovieNavHost(navController : NavHostController) {

    val screens = listOf(
        MovieScreens.OverviewScreen,
        MovieScreens.DetailScreen
    )

    NavHost(
        navController = navController,
        startDestination = MovieScreens.OverviewScreen.route
    ){
        composable(route = MovieScreens.OverviewScreen.route){
            OverviewScreen()
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MadLevel4Task2Theme {
        //Greeting("Android")
    }
}