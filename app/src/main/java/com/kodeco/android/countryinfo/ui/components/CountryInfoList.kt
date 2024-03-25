package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.sample.sampleCountries

@Composable
fun CountryInfoList(
    countries: List<Country>,
    onCountryRowTap: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    var selectedCountry: Country? by remember { mutableStateOf(null) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = onRefresh,
            ) {
                Text(text = "Refresh")
            }
        }

        LazyColumn {
            itemsIndexed(countries) { index, country ->
                CountryInfoRow(country) {
                    onCountryRowTap(index)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CountryInfoListPreview() {
    val countries = sampleCountries
    val onCountryRowTap: (Int) -> Unit = { index ->
        println("Country tapped: $index")
    }
    val onRefresh: () -> Unit = {
        println("Refreshing countries")
    }
    CountryInfoList(countries = countries, onCountryRowTap = onCountryRowTap, onRefresh = onRefresh)
}