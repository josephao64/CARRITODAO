package com.example.poyectoprogra2.caritoCompras

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrito")
data class Carrito(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String, // ID del usuario asociado al carrito
    val productoId: Int,
    val cantidad: Int
)
