package com.example.poyectoprogra2.caritoCompras

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey val id: String,
    val nombre: String,
    // Otros campos de usuario
)