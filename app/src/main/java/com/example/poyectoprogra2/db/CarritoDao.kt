package com.example.poyectoprogra2.db

import androidx.room.*
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {
    // Obtiene todos los productos en el carrito de un usuario específico
    @Query("SELECT * FROM productos_carrito WHERE usuarioId = :usuarioId")
    fun getProductosCarrito(usuarioId: String): Flow<List<ProductoCarrito>>

    // Inserta un nuevo producto en el carrito o actualiza uno existente
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProducto(productoCarrito: ProductoCarrito)

    // Elimina un producto específico del carrito
    @Query("DELETE FROM productos_carrito WHERE id = :productoId")
    suspend fun eliminarProducto(productoId: String)

    // Actualiza la cantidad de un producto específico en el carrito
    @Query("UPDATE productos_carrito SET cantidad = :nuevaCantidad WHERE id = :productoId")
    suspend fun actualizarCantidadProducto(productoId: String, nuevaCantidad: Int)

    // Puedes agregar aquí más métodos según sea necesario
}

