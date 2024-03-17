package com.kodeco.android.countryinfo.repositories

import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.models.CountryFlags
import com.kodeco.android.countryinfo.models.CountryName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.withIndex

class CountryRepositoryImpl : CountryRepository {

    private val countries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())

    init {
        // Initialize the cache with some dummy data
        countries.value = listOf(
            Country(
                CountryName("United States"),
                listOf("Washington D.C."),
                331000000,
                9833520f,
                CountryFlags("https://flag-url.com/usa.png", "https://flag-url.com/usa.svg")
            ),
            Country(
                CountryName("Canada"),
                listOf("Ottawa"),
                37590000,
                9976140f,
                CountryFlags("https://flag-url.com/canada.png", "https://flag-url.com/canada.svg")
            ),
            Country(
                CountryName("United Kingdom"),
                listOf("London"),
                67800000,
                242495f,
                CountryFlags("https://flag-url.com/uk.png", "https://flag-url.com/uk.svg")
            )
        )
    }


    override fun fetchCountries(): Flow<List<Country>> {
        TODO("Not yet implemented")
        return countries.asStateFlow()
    }

    override fun getCountry(countryId: Int): Country? {
        TODO("Not yet implemented")
        // Check if the index is valid
        if (countryId >= 0 && countryId < countries.value.size) {
            // Return the country at the specified index
            return countries.value[countryId]
        }
        // Return null if the index is out of bounds
        return null
    }
}