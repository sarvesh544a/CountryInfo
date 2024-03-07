package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.model.CountryFlags
import com.kodeco.android.countryinfo.model.CountryName


@Composable
fun CountryInfoRow(country: Country) {
    // Display country information here
    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp))
    ) {
        Text(
            text = " Name: ${country.commonName} \n Capital: ${country.capital?.get(0)}",
            modifier = Modifier.padding(5.dp),
            lineHeight = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CountryInfoRowPreview() {
    val country = Country(
        CountryName("United States"),
        listOf("Washington D.C."),
        331002651L,
        9833515.0,
        CountryFlags("https://example.com/us_flag.png")
    )
    CountryInfoRow(country = country)
}