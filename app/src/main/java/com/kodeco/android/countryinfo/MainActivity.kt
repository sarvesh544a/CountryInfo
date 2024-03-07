package com.kodeco.android.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kodeco.android.countryinfo.ui.components.CountryListScreen
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                CountryListScreen()
            }
        }
    }
}
