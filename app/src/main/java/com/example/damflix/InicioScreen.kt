package com.example.damflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.damflix.ui.theme.DAMflixTheme

@Composable
fun InicioScreenStart() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido a DAMflix")
        Text(text = "Version: 1.4")
    }
}