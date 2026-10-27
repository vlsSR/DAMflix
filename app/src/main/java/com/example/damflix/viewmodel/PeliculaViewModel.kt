package com.example.damflix.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.damflix.model.Pelicula
import com.example.damflix.model.PeliculaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class PeliculaViewModel(private val repository: PeliculaRepository) : ViewModel() {

    private val _listaPeliculas = MutableStateFlow<List<Pelicula>>(emptyList())
    val listaPeliculas: StateFlow<List<Pelicula>> = _listaPeliculas.asStateFlow()

    init {
        cargarPeliculas()
    }

    fun cargarPeliculas() {
        viewModelScope.launch {
            _listaPeliculas.value = repository.obtenerTodas()
        }
    }

    fun agregarPelicula(titulo: String, director: String, estreno: Int, esVista: Boolean) {
        viewModelScope.launch {
            val nueva = Pelicula(titulo = titulo, director = director, estreno = estreno, esVista = esVista)
            repository.insertar(nueva)
            cargarPeliculas()
        }
    }

    fun conmutarFavorito(pelicula: Pelicula) {
        viewModelScope.launch {
            val actualizada = pelicula.copy(esFavorita = !pelicula.esFavorita)
            repository.actualizar(actualizada)
            cargarPeliculas()
        }
    }
}