package com.kodeco.android.countryinfo.model

sealed class CountryState {
    object Loading : CountryState()
    data class Success(val countries: List<Country>) : CountryState()
    data class Error(val message: String) : CountryState()
}
