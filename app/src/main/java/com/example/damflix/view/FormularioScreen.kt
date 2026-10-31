package com.example.damflix.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.damflix.R
import com.example.damflix.viewmodel.PeliculaViewModel

@Composable
fun FormularioScreenStart(
    viewModel: PeliculaViewModel,
    onPeliculaGuardada: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var estreno by remember { mutableStateOf("") }
    var esVista by remember { mutableStateOf(false) }

    val esFormularioValido = titulo.isNotBlank() && director.isNotBlank() && estreno.isNotBlank()


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.form_welcome), style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text(stringResource(R.string.form_title)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = director,
            onValueChange = { director = it },
            label = { Text(stringResource(R.string.form_director)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estreno,
            onValueChange = { estreno = it },
            label = { Text(stringResource(R.string.form_year)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically){
            Checkbox(checked = esVista, onCheckedChange = { esVista = it })
            Text(stringResource(R.string.form_watched))
        }

        Button(
            onClick = {
                val estrenoInt = estreno.toIntOrNull() ?: 0
                viewModel.agregarPelicula(
                    titulo = titulo,
                    director = director,
                    estreno = estrenoInt,
                    esVista = esVista
                )
                titulo = ""
                director = ""
                estreno = ""
                esVista = false
                onPeliculaGuardada()
            },
            enabled = esFormularioValido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.form_save))
        }
    }

}