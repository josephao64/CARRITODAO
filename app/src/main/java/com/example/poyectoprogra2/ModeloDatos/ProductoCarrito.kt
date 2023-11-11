package com.example.poyectoprogra2.ModeloDatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos_carrito")
data class ProductoCarrito(
    @PrimaryKey val id: String,
    val nombre: String,
    val precio: Double,
    val cantidad: Int,
    val usuarioId: String
)
