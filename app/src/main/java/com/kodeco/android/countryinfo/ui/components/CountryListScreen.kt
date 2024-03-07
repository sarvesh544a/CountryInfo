package com.kodeco.android.countryinfo.ui.components


import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.CountriesInfoService
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.model.CountryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

val retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v3.1/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val service = retrofit.create(CountriesInfoService::class.java)

@Composable
fun CountryListScreen() {
    var countryState by remember { mutableStateOf<CountryState>(CountryState.Loading) }

    LaunchedEffect(true) {
        countryState = try {
            val countries = fetchCountries()
            CountryState.Success(countries)
        } catch (e: IOException) {
            CountryState.Error("Failed to fetch data. Check your internet connection.")
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize())
    {
        when (val state = countryState) {
            is CountryState.Loading -> Loading(size = 32.dp)
            is CountryState.Success -> CountryInfoList(state.countries)
            is CountryState.Error -> CountryErrorScreen(state.message)
        }

    }
}

suspend fun fetchCountries(): List<Country> {
    return withContext(Dispatchers.IO) {
        service.getAllCountries()
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListScreenPreview() {
    CountryListScreen()
}