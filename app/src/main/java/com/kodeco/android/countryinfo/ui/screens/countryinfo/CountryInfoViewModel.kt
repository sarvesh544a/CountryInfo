import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountryInfoViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryInfoState>(CountryInfoState.Loading)
    val uiState: StateFlow<CountryInfoState> = _uiState

    init {
        fetchCountryInfo()
    }

    private fun fetchCountryInfo() {
        viewModelScope.launch {
            repository.fetchCountries().collect {
                _uiState.value = it
            }
        }
    }

    fun refreshData() {
        _uiState.value = CountryInfoState.Loading
        fetchCountryInfo()
    }
}
