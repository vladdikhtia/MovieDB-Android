package nl.hva.madlevel4task2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import nl.hva.madlevel4task2.R
import nl.hva.madlevel4task2.viewModel.MoviesViewModel


@Composable
fun DetailScreen(navController : NavController, viewModel : MoviesViewModel) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
//            model = viewModel.selectedMovie?.backdrop_path, //posterURL
            // https://image.tmdb.org/t/p/original/${movie.poster_path}
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original/${viewModel.selectedMovie?.backdrop_path}")
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(), //posterURL

            contentDescription = viewModel.selectedMovie?.backdrop_path.toString(), //posterURL
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Gray)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original/${viewModel.selectedMovie?.poster_path}") //imageURL
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = viewModel.selectedMovie?.poster_path.toString(),//imageURL
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = viewModel.selectedMovie?.title.toString(), //name of the movie
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                    )
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Icon(
                            Icons.Default.Star, "Star",
                            modifier = Modifier.size(27.dp)
                        )
                        // rating of the movie
                        val formattedVoteAverage =
                            "%.1f".format(viewModel.selectedMovie?.vote_average ?: 0.0)

                        Text(
                            text = formattedVoteAverage,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.overview),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = viewModel.selectedMovie?.overview.toString(), // plot of the movie
                style = MaterialTheme.typography.body1,
            )


        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(navController = rememberNavController(), viewModel = MoviesViewModel())
}