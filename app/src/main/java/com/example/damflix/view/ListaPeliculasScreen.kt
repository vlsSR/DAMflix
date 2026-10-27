package com.example.damflix.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.damflix.model.Pelicula
import com.example.damflix.viewmodel.PeliculaViewModel
import com.example.damflix.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPeliculasScreenStart(
    viewModel: PeliculaViewModel,
    modifier: Modifier = Modifier
) {
    val peliculas by viewModel.listaPeliculas.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = peliculas,
            key = { p -> p.id }
        ) { pelicula ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = pelicula.titulo,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "${pelicula.director} • ${pelicula.estreno}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    IconButton(onClick = { viewModel.conmutarFavorito(pelicula) }) {
                        Icon(
                            imageVector = if (pelicula.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.btn_aceptar),
                            tint = if (pelicula.esFavorita) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}