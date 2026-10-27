package com.example.damflix.model

data class Pelicula(
    val id: Int = 0,
    val titulo: String,
    val director: String,
    val estreno: Int,
    var esVista: Boolean = false,
    var esFavorita: Boolean = false
)