package com.example.damflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.damflix.ui.theme.DAMflixTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPeliculasScreenStart() {
    Scaffold(

    ) { innerPadding ->
        Box (modifier = Modifier.padding(innerPadding)) {
            val peliculas = remember {
                mutableStateListOf(
                    Pelicula(1, "El Padrino", "Francis Ford Coppola", "1972", true),
                    Pelicula(2, "El Caballero Oscuro", "Christopher Nolan", "2008", true),
                    Pelicula(3, "Pulp Fiction", "Quentin Tarantino", "1994", false),
                    Pelicula(4, "Forrest Gump", "Robert Zemeckis", "1994", true)
                )
            }
            listaPeliculas(peliculas, onToggleFavorito = { id ->
                val index = peliculas.indexOfFirst { it.id == id }
                val peli = peliculas[index]
                peliculas[index] = peli.copy(esFavorita = !peli.esFavorita)
            })

        }
    }
}

@Composable
fun listaPeliculas(peliculas: List<Pelicula>, onToggleFavorito: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = peliculas, key = { peli -> peli.id }) { pelicula ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = pelicula.titulo, modifier = Modifier.weight(1f))
                    IconButton(onClick = { onToggleFavorito(pelicula.id) }) {
                        Icon(
                            imageVector = if (pelicula.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (pelicula.esFavorita) Color.Red else Color.Gray
                        )
                    }
                }
            }

        }
    }
}