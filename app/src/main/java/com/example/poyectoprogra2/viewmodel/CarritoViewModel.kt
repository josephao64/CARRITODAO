package com.example.poyectoprogra2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poyectoprogra2.db.CarritoDao
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarritoViewModel(private val carritoDao: CarritoDao) : ViewModel() {
    private val _productosCarrito = MutableStateFlow<List<ProductoCarrito>>(emptyList())
    val productosCarrito: StateFlow<List<ProductoCarrito>> = _productosCarrito

    fun cargarProductos(usuarioId: String) {
        viewModelScope.launch {
            carritoDao.getProductosCarrito(usuarioId).collect { productos ->
                _productosCarrito.value = productos
            }
        }
    }

    fun agregarProductoAlCarrito(producto: ProductoCarrito) {
        viewModelScope.launch {
            carritoDao.insertarProducto(producto)
            cargarProductos(producto.usuarioId)
        }
    }

    fun eliminarProductoDelCarrito(productoId: String) {
        viewModelScope.launch {
            carritoDao.eliminarProducto(productoId)
            // Aquí podrías recargar la lista de productos o manejar actualizaciones
        }
    }

    fun actualizarCantidadProducto(productoId: String, nuevaCantidad: Int) {
        viewModelScope.launch {
            carritoDao.actualizarCantidadProducto(productoId, nuevaCantidad)
            // Actualizar la vista o manejar lógica adicional
        }
    }

    fun calcularTotalCarrito(): Double {
        return _productosCarrito.value.sumOf { it.precio.toDouble() * it.cantidad }
    }
}
