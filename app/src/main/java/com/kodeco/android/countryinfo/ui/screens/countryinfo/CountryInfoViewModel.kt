import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountryInfoViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryInfoState>(CountryInfoState.Loading(0))
    val uiState: StateFlow<CountryInfoState> = _uiState

    private var uptimeCounter = 0

    init {
        fetchCountryInfo()
        startUptimeCounter()
    }

    private fun fetchCountryInfo() {
        viewModelScope.launch {
            repository.fetchCountries(uptimeCounter).collect {
                _uiState.value = it
            }
        }
    }

    fun refreshData() {
        _uiState.value = CountryInfoState.Loading(uptimeCounter)
        fetchCountryInfo()
    }

    private fun startUptimeCounter() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                uptimeCounter++
                if (_uiState.value is CountryInfoState.Loading) {
                    _uiState.value = CountryInfoState.Loading(uptimeCounter)
                }
            }
        }
    }

}
