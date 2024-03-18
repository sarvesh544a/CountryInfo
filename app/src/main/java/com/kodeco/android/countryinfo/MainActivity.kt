package com.kodeco.android.countryinfo

import CountryInfoViewModel
import CountryInfoViewModelFactory
import CountryRepositoryImpl
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kodeco.android.countryinfo.network.CountryService
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service: CountryService = retrofit.create(CountryService::class.java)

        val countryRepository = CountryRepositoryImpl(service) // Initialize your CountryRepositoryImpl
        val viewModelFactory = CountryInfoViewModelFactory(countryRepository) // Initialize ViewModelFactory
        var viewModel = ViewModelProvider(this, viewModelFactory).get(CountryInfoViewModel::class.java) // Create ViewModel

        setContent {
            MyApplicationTheme {
                CountryInfoScreen(viewModel)
            }
        }
    }
}
