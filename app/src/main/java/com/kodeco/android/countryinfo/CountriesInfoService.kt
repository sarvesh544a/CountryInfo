package com.kodeco.android.countryinfo

import com.kodeco.android.countryinfo.model.Country

import retrofit2.Response
import retrofit2.http.GET

interface CountriesInfoService {
    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>>
}



