package com.kodeco.android.countryinfo.ui.screens.countryinfo

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.sample.sampleCountries
import com.kodeco.android.countryinfo.ui.components.Error
import com.kodeco.android.countryinfo.ui.components.CountryInfoList
import com.kodeco.android.countryinfo.ui.components.Loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun CountryInfoScreen(
    viewModel: CountryInfoViewModel,
    onCountryRowTap: (Int) -> Unit // Lambda function to handle country row tap
) {
    val state = viewModel.uiState.collectAsState()
    Surface {
        when(val countryInfoState = state.value) {
            is CountryInfoState.Loading -> Loading(countryInfoState.uptime)
            is CountryInfoState.Success -> CountryInfoList(
                countries = countryInfoState.countries,
                onCountryRowTap = onCountryRowTap // Pass the lambda function to CountryInfoList
            ) {
                viewModel.fetchCountries()
            }
            is CountryInfoState.Error -> Error(countryInfoState.error) {
                viewModel.fetchCountries()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CountryInfoScreenPreview() {
    val mockRepository = object : CountryRepository {
        override suspend fun fetchCountries(): Flow<List<Country>> {
            return flowOf(sampleCountries)
        }
        override fun getCountry(countryId: Int): Country? {
            TODO("Not yet implemented")
        }
    }

    val mockViewModel = CountryInfoViewModel(repository = mockRepository)
    val onCountryRowTap: (Int) -> Unit = {}
    CountryInfoScreen(viewModel = mockViewModel, onCountryRowTap = onCountryRowTap)
}
