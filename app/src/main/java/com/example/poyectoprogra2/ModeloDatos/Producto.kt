package com.example.poyectoprogra2.ModeloDatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String // URL a la imagen del producto
)
