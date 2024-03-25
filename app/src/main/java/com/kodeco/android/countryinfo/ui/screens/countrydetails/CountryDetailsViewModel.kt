package com.kodeco.android.countryinfo.ui.screens.countrydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoViewModel

class CountryDetailsViewModel(
    private val countryId: Int,
    private val repository: CountryRepository,
) : ViewModel() {

    var country: Country
    init {
        country = repository.getCountry(countryId)!!
    }
    class CountryDetailsViewModelFactory(private val countryId: Int,
                                         private val repository: CountryRepository,) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryDetailsViewModel(countryId, repository) as T
    }
}

