package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun Loading(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    Box(modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.CircularProgressIndicator(
            color = Color.Blue,
            strokeWidth = 4.dp
        )
    }
}

@Preview
@Composable
fun LoadingPreview() {
    Loading(size = 32.dp)
}
