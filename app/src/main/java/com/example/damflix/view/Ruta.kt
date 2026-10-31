package com.example.damflix.view

import com.example.damflix.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.damflix.viewmodel.PeliculaViewModel
import kotlinx.coroutines.launch

sealed class Ruta(val path: String, val label: String, val icon: ImageVector) {
    object Inicio: Ruta(
        path = "inicio",
        label = "Inicio",
        icon = Icons.Filled.Home
    )
    object Listado: Ruta(
        path = "listado",
        label = "Listado",
        icon = Icons.Filled.List
    )
    object Formulario: Ruta(
        path = "alta",
        label = "Añadir",
        icon = Icons.Filled.Add
    )
    object Perfil: Ruta(
        path = "perfil",
        label = "Perfil",
        icon = Icons.Filled.AccountCircle
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDAMFlixMainScreen(viewModel: PeliculaViewModel) {
    val navController = rememberNavController()
    // 1. Estado del Drawer (Abierto / Cerrado)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // 2. CoroutineScope necesario para animar la apertura/cierre
    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val opcionesMenu = listOf(Ruta.Inicio, Ruta.Listado, Ruta.Formulario, Ruta.Perfil)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Cabecera del Menú Lateral
                Box(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Text(
                        text = "DAMFlix - Room ORM",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // Opciones editables del menú
                opcionesMenu.forEach { opcion ->
                    NavigationDrawerItem(
                        icon = { Icon(opcion.icon, contentDescription = opcion.label) },
                        label = { Text(opcion.label) },
                        selected = rutaActual == opcion.path,
                        onClick = {
                            // Cierre asíncrono del panel
                            coroutineScope.launch { drawerState.close() }
                            if (rutaActual != opcion.path) {
                                navController.navigate(opcion.path) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        // Contenido Principal envuelto en Scaffold
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("DAMFlix") },
                    navigationIcon = {
                        // Botón de hamburguesa para abrir el Drawer
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.menu_drawer_text))
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Ruta.Inicio.path,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Ruta.Inicio.path) {
                    InicioScreenStart()
                }
                composable(Ruta.Listado.path) {
                    ListaPeliculasScreenStart(viewModel = viewModel)
                }
                composable(Ruta.Formulario.path) {
                    FormularioScreenStart(
                        viewModel = viewModel,
                        onPeliculaGuardada = {
                            navController.navigate(Ruta.Formulario.path) {
                                popUpTo(Ruta.Inicio.path) { saveState = true }
                            }
                        }
                    )
                }
                composable(Ruta.Perfil.path) {
                    ProfileScreenStart()
                }
            }
        }
    }
}