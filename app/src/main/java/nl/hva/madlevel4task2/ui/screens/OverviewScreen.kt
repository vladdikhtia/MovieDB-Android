package nl.hva.madlevel4task2.ui.screens

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.hva.madlevel4task2.R
import nl.hva.madlevel4task2.data.api.util.Resource
import nl.hva.madlevel4task2.data.model.ListOfMovies
import nl.hva.madlevel4task2.viewModel.MoviesViewModel
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import nl.hva.madlevel4task2.data.model.Movie

@Composable
fun OverviewScreen(viewModel : MoviesViewModel, navigateToDetailScreen: () -> Unit) {

    val movieResource : Resource<ListOfMovies>? by viewModel.movieResource.observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally
    ){

        SearchView(viewModel = viewModel)

        when (movieResource) {
            is Resource.Success ->
                LazyVerticalGrid(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .align(Alignment.CenterHorizontally),
                    columns = GridCells.Fixed(2),
                    content = {
                        val movies : List<Movie>? = movieResource?.data?.results
                        if (!movies.isNullOrEmpty()) {
                            items(movies.size) { iterator ->
                                val movie : Movie = movies[iterator]
                                MoviePoster(
                                "https://image.tmdb.org/t/p/original/${movie.poster_path}" , onClick = {
                                    viewModel.selectedMovie = movie
                                    navigateToDetailScreen()
                                })
                            }
                        }
                    })

            is Resource.Error -> {
                Text(
                    text = viewModel.movieResource.value?.message.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 200.dp)
                )
            }

            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .padding(top = 200.dp)
                        .align(CenterHorizontally),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

            else -> stringResource(R.string.something_wrong_state)
        }
    }


}


@Composable
fun MoviePoster(url : String, onClick : () -> Unit) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = url,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .width(350.dp)
                .height(250.dp)
                .clickable { onClick() },

            )

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchView(viewModel : MoviesViewModel) {

    val searchQueryState = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = searchQueryState.value,
        onValueChange = { value ->
            searchQueryState.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),

        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            IconButton(onClick = {
                    viewModel.getMovie(searchQueryState.value.text)

                //based on @ExperimentalComposeUiApi - if this doesn't work in a newer version remove it
                //no alternative in compose for hiding keyboard at time of writing
                keyboardController?.hide()
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    tint = Color.White
                )
            }
        },
        trailingIcon = {
            if (searchQueryState.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        searchQueryState.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                        tint = Color.White
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_movie_hint),
                color = Color.White
            )
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            containerColor = Color.Blue,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            disabledIndicatorColor = Color.White,
        )
    )
}

