package com.kodeco.android.countryinfo.ui.countrylist

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


@Preview
@Composable
fun CountryInfoListPreview() {
    // Preview function to display a preview of the CountryList

    //CountryList(countries = countries)
}