package uabc.ic.benjaminbolanos.rubiksrace.highscores_view

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.Highscore
import uabc.ic.benjaminbolanos.rubiksrace.highscore_database.HighscoreRepository

class HighscoreViewModel(private val repository: HighscoreRepository): ViewModel() {
    val allHighscores: LiveData<List<Highscore>> = repository.allHighscores.asLiveData()
    val orderedHighscores: LiveData<List<Highscore>> = repository.orderedHighscores.asLiveData()

    fun insert(highscore: Highscore) = viewModelScope.launch{
        repository.insert(highscore)
    }

    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }
}

class HighscoreViewModelFactory(private val repository: HighscoreRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighscoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HighscoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}