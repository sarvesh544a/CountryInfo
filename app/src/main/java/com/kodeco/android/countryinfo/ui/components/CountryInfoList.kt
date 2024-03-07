package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.model.CountryFlags
import com.kodeco.android.countryinfo.model.CountryName

@Composable
fun CountryInfoList(countries: List<Country>) {
    LazyColumn(modifier = Modifier.padding(top = 16.dp)
        .background(color = Color.DarkGray)
    ) {
        items(countries) { country ->
            CountryInfoRow(country = country)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CountryInfoListPreview() {
    val countries = listOf(
        Country(
            CountryName("United States"),
            listOf("Washington D.C."),
            331002651L,
            9833515.0,
            CountryFlags("https://example.com/us_flag.png")
        ),
        Country(
            CountryName("Canada"),
            listOf("Ottawa"),
            37742154L,
            9984670.0,
            CountryFlags("https://example.com/ca_flag.png")
        ),
        // Add more sample countries as needed
    )
    CountryInfoList(countries)
}