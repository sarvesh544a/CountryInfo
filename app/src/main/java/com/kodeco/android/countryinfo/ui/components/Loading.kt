package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.flow.Flows
import kotlinx.coroutines.delay

@Composable
fun Loading() {
    val counterValue by Flows.counterFlow.collectAsState()

    LaunchedEffect(Unit) {
        delay(10000) // Simulate a loading delay of 2 seconds
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Current Counter Value: $counterValue")
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun LoadingPreview() {
    Loading()
}
