package com.example.damflix

import android.app.Application
import com.example.damflix.data.PeliculaRepository
import com.example.damflix.data.local.AppDatabase

class DAMFlixApplication : Application() {
    // 1. La Base de Datos solo se crea cuando se acceda a ella por primera vez
    val database by lazy { AppDatabase.getDatabase(this) }

    // 2. El Repositorio se crea cuando se necesite, recibiendo el DAO de la BD perezosa
    val repository by lazy { PeliculaRepository(database.peliculaDao()) }
}