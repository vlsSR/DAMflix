package com.example.damflix.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.damflix.R

@Composable
fun InicioScreenStart() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.info_welcome))
        Text(stringResource(R.string.info_version))
    }
}