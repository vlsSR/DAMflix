package com.example.damflix

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.damflix.ui.theme.DAMflixTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.damflix.model.DAMFlixDbHelper
import com.example.damflix.model.PeliculaRepository
import com.example.damflix.view.FormularioScreenStart
import com.example.damflix.view.InicioScreenStart
import com.example.damflix.view.ListaPeliculasScreenStart
import com.example.damflix.view.ProfileScreenStart
import com.example.damflix.view.Ruta
import com.example.damflix.viewmodel.PeliculaViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
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
                            title = { Text(stringResource(R.string.title_topbar)) },
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
                    val dbHelper = DAMFlixDbHelper(this)

                    val repositorio = PeliculaRepository(dbHelper)

                    val viewModelPeliculas = PeliculaViewModel(repositorio)
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
                            ListaPeliculasScreenStart(viewModelPeliculas)
                        }
                        composable(Ruta.Formulario.path) {
                            FormularioScreenStart(viewModelPeliculas)
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


