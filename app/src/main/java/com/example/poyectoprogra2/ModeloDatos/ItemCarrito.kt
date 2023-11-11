package com.example.poyectoprogra2.ModeloDatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrito")
data class ItemCarrito(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productoId: Int,
    val cantidad: Int
)