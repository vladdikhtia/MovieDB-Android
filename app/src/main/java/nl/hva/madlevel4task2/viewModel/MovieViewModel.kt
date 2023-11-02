package nl.hva.madlevel4task2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.hva.madlevel4task2.data.api.util.Resource
import nl.hva.madlevel4task2.data.model.ListOfMovies
import nl.hva.madlevel4task2.repository.MovieRepository

class MovieViewModel(application : Application) : AndroidViewModel(application) {

    //reference to the MovieRepository()
    private val movieRepository = MovieRepository()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val movieResource : LiveData<Resource<ListOfMovies>>
        get() = _movieResource

    //inialize it with an empty type of Resource
    private val _movieResource : MutableLiveData<Resource<ListOfMovies>> = MutableLiveData(Resource.Empty())

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getMovie(){
        //set resource type to loading
        _movieResource.value = Resource.Loading()

        viewModelScope.launch {
            _movieResource.value = movieRepository.getMovies()
        }
    }
}
