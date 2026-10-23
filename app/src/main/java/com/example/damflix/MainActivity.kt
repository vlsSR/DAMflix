package com.example.damflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.damflix.ui.theme.DAMflixTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DAMflixTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("DAMFlix - Catálogo") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    },
                    bottomBar = {
                        // Observamos la ruta actual del BackStack
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val rutaActual = navBackStackEntry?.destination?.route

                        NavigationBar {
                            // Definimos una lista inmutable con las rutas
                            listOf(
                                Ruta.Inicio,
                                Ruta.Listado,
                                Ruta.Formulario,
                                Ruta.Perfil
                            ).forEach { ruta ->
                                NavigationBarItem(
                                    selected = rutaActual == ruta.path,
                                    onClick = {
                                        if (rutaActual != ruta.path) {
                                            navController.navigate(ruta.path) {
                                                popUpTo(navController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    icon = { Icon(ruta.icon, contentDescription = ruta.label) },
                                    label = { Text(ruta.label) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    // Es obligatorio aplicar innerPadding al NavHost para no superponer vistas
                    NavHost(
                        navController = navController,
                        startDestination = Ruta.Inicio.path,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Ruta.Inicio.path) {
                            InicioScreenStart()
                        }
                        composable(Ruta.Listado.path) {
                            ListaPeliculasScreenStart()
                        }
                        composable(Ruta.Formulario.path) {
                            FormularioScreenStart()
                        }
                        composable(Ruta.Perfil.path) {
                            ProfileScreenStart()
                        }
                    }
                }


            }
        }
    }
}


