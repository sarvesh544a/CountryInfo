package com.kodeco.android.countryinfo.ui.screens.countryinfo

import CountryInfoViewModel
import android.os.Parcelable
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.ui.components.CountryInfoList
import com.kodeco.android.countryinfo.ui.components.Error
import com.kodeco.android.countryinfo.ui.components.Loading
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CountryInfoState : Parcelable {
    data class Loading(val uptimeCounter: Int) : CountryInfoState()
    data class Success(val countries: List<Country>) : CountryInfoState()
    data class Error(val error: Throwable) : CountryInfoState()
}

@Composable
fun CountryInfoScreen(
    viewModel: CountryInfoViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    // Create mutable state variables for tapCounter and backCounter
    var tapCounter = remember { mutableStateOf(0) }
    val backCounter = remember { mutableStateOf(0) }

    Surface {
        when (val curState = uiState) {
            is CountryInfoState.Loading -> Loading(curState.uptimeCounter)
            is CountryInfoState.Success -> CountryInfoList(
                countries = curState.countries,
                onRefreshClick = { viewModel.refreshData() },
                tapCounter = tapCounter,
                backCounter = backCounter
            )
            is CountryInfoState.Error -> Error(curState.error) {
                // Handling retry action, you can leave this empty for now
            }
        }
    }

}


