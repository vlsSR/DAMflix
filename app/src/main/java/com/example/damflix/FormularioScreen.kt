package com.example.damflix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormularioScreenStart() {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var estreno by remember { mutableStateOf("") }
    var esVista by remember { mutableStateOf(false) }


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título de la Película") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("Director de la Pelicula") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estreno,
            onValueChange = { estreno = it },
            label = { Text("Año de estreno de la pelicula") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically){
            Checkbox(checked = esVista, onCheckedChange = { esVista = it })
            Text("Ya he visto esta película")
        }

        Button(
            onClick = { /* Guardar película */ },
            enabled = titulo.isNotBlank() && director.isNotBlank(), // Validación
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Película")
        }
    }

}