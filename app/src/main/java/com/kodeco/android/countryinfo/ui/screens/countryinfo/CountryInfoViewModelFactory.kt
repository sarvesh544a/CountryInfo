import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountryInfoViewModelFactory(private val repository:
                                  CountryRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CountryInfoViewModel(repository) as T
}
