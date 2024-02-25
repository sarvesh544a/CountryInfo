package com.kodeco.android.countryinfo.ui.countrylist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.CountriesInfoService
import com.kodeco.android.countryinfo.model.Country
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Retrofit setup
val retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v3.1/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val countriesService = retrofit.create(CountriesInfoService::class.java)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountryInfoScreen() {
    var countries by rememberSaveable { mutableStateOf<List<Country>>(emptyList()) }

    // LaunchedEffect to trigger the network request
    LaunchedEffect(true) {
        val response = countriesService.getAllCountries()
        if (response.isSuccessful) {
            countries = response.body() ?: emptyList()
        } else {
            // Handle error
            // For example, show a snackbar with error message
            // You can use ScaffoldState to show snackbar
        }
    }

    // Display the list of countries
    //Scaffold(content = { CountryList(countries) })

    Surface(
        modifier = Modifier.fillMaxSize())
    {
        CountryInfoList(countries)
    }
}


// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoScreenPreview() {

}