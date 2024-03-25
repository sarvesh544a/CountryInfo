package com.kodeco.android.countryinfo.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsViewModel
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoViewModel

@Composable
fun CountryInfoNavHost(
    repository: CountryRepository,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list",
            arguments = listOf(navArgument("countryIndex") { defaultValue = -1 })
        ) {

            CountryInfoScreen(
                viewModel = viewModel(
                    factory = CountryInfoViewModel.CountryInfoViewModelFactory(
                        repository = repository,
                    ),
                ),
                onCountryRowTap = { countryIndex ->
                    navController.navigate("details/$countryIndex")
                }
            )
        }
        composable("details/{countryIndex}",
            ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val countryIndex = arguments.getString("countryIndex")!!.toInt()

            val viewModel: CountryDetailsViewModel = viewModel(
                factory = CountryDetailsViewModel.CountryDetailsViewModelFactory(
                    countryId = countryIndex, // Assuming countryId is used for navigation
                    repository = repository
                )
            )
            CountryDetailsScreen(
                viewModel = viewModel,
                onNavigateUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}