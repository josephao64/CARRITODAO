package com.example.poyectoprogra2.ModeloDatos

import androidx.room.Embedded
import androidx.room.Relation

data class ProductoConCantidad(
    @Embedded val producto: Producto,
    @Relation(
        parentColumn = "id",
        entityColumn = "productoId"
    )
    val itemCarrito: ItemCarrito
)