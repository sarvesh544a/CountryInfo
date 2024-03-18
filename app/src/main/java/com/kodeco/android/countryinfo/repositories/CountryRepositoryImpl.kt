import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.models.CountryFlags
import com.kodeco.android.countryinfo.models.CountryName
import com.kodeco.android.countryinfo.network.CountryService
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl(private val service: CountryService) : CountryRepository {

    private var countriesList: List<Country> = emptyList()

    override fun fetchCountries(uptimeCounter: Int): Flow<CountryInfoState> {
        return flow {
            emit(CountryInfoState.Loading(uptimeCounter))
            delay(1_000) // Simulate delay
            val countriesResponse = service.getAllCountries()
            val newState = if (countriesResponse.isSuccessful) {
                countriesList = countriesResponse.body()!!
                CountryInfoState.Success(countriesResponse.body()!!)
            } else {
                CountryInfoState.Error(Throwable("Request failed: ${countriesResponse.message()}"))
            }
            emit(newState)
        }
    }

    override suspend fun getCountry(uniqueId: String): Country? {
        return countriesList.find { it.name == CountryName(uniqueId) }
    }
}
