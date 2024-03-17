package com.kodeco.android.countryinfo.ui.screens.countryinfo

import CountryInfoViewModel
import android.os.Parcelable
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.network.CountryService
import com.kodeco.android.countryinfo.sample.sampleCountries
import com.kodeco.android.countryinfo.ui.components.CountryInfoList.CountryInfoList
import com.kodeco.android.countryinfo.ui.components.Error.Error
import com.kodeco.android.countryinfo.ui.components.Loading.Loading
import kotlinx.parcelize.Parcelize
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

@Parcelize
sealed class CountryInfoState : Parcelable {
    data object Loading : CountryInfoState()
    data class Success(val countries: List<Country>) : CountryInfoState()
    data class Error(val error: Throwable) : CountryInfoState()
}

@Composable
fun CountryInfoScreen(
    viewModel: CountryInfoViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface {
        when (val curState = uiState) {
            is CountryInfoState.Loading -> Loading()
            is CountryInfoState.Success -> CountryInfoList(
                countries = curState.countries,
                onRefreshClick = { viewModel.refreshData() } // Trigger refreshData() on button click
            )
            is CountryInfoState.Error -> Error(curState.error) {
                // Handling retry action, you can leave this empty for now
            }
        }
    }

}


