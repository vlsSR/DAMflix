package com.example.damflix

data class Pelicula(val id: Int, val titulo: String, val director: String, val estreno: String, var esVista: Boolean, var esFavorita: Boolean = false)
