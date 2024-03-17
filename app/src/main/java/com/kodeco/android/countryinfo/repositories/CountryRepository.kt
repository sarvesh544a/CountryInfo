import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoState
import kotlinx.coroutines.flow.Flow


interface CountryRepository {
    fun fetchCountries(): Flow<CountryInfoState>
    suspend fun getCountry(uniqueId: String): Country?
}
