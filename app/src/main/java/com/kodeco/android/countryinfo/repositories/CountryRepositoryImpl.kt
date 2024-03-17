import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.models.CountryFlags
import com.kodeco.android.countryinfo.models.CountryName
import com.kodeco.android.countryinfo.network.CountryService
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl(private val service: CountryService) : CountryRepository {

    // Simulated list of countries
    private val countriesList = listOf(
        Country(
            name = CountryName("USA"),
            capital = listOf("Washington"),
            population = 328_200_000,
            area = 9_833_520f,
            flags = CountryFlags(png = "usa.png", svg = "usa.svg")
        ),
        Country(
            name = CountryName("Canada"),
            capital = listOf("Ottawa"),
            population = 37_600_000,
            area = 9_984_670f,
            flags = CountryFlags(png = "canada.png", svg = "canada.svg")
        ),
        Country(
            name = CountryName("Australia"),
            capital = listOf("Canberra"),
            population = 25_500_000,
            area = 7_692_024f,
            flags = CountryFlags(png = "australia.png", svg = "australia.svg")
        )
    )

    override fun fetchCountries(): Flow<CountryInfoState> {
        return flow {
            emit(CountryInfoState.Loading)
            delay(1_000) // Simulate delay
            val countriesResponse = service.getAllCountries()
            val newState = if (countriesResponse.isSuccessful) {
                CountryInfoState.Success(countriesResponse.body()!!)
            } else {
                CountryInfoState.Error(Throwable("Request failed: ${countriesResponse.message()}"))
            }
            emit(newState)
        }
    }

    override suspend fun getCountry(uniqueId: String): Country? {
        return TODO("Provide the return value")
    }
}
