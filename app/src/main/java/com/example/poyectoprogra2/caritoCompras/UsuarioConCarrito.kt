package com.example.poyectoprogra2.caritoCompras

import androidx.room.Embedded
import androidx.room.Relation

data class UsuarioConCarrito(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val carrito: List<Carrito>
)
