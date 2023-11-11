package com.example.poyectoprogra2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito

import com.example.poyectoprogra2.db.AppDatabase
import com.example.poyectoprogra2.viewmodel.CarritoViewModel

@Composable
fun CarritoPantalla() {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val carritoDao = remember { database.carritoDao() }
    val carritoViewModel = remember { CarritoViewModel(carritoDao) }

    LaunchedEffect(key1 = userId) {
        carritoViewModel.cargarProductos(userId)
    }

    val productosCarrito = carritoViewModel.productosCarrito.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Carrito de Compras", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(productosCarrito) { producto ->
                ProductoCarritoItem(producto, carritoViewModel)
            }
        }

        val total = carritoViewModel.calcularTotalCarrito()
        Text("Total: $total", style = MaterialTheme.typography.headlineSmall)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoCarritoItem(producto: ProductoCarrito, carritoViewModel: CarritoViewModel) {
    var cantidad by remember { mutableStateOf(producto.cantidad) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
            Text("Precio: ${producto.precio}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Cantidad:")
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = cantidad.toString(),
                    onValueChange = { nuevaCantidad ->
                        cantidad = nuevaCantidad.toIntOrNull() ?: cantidad
                    },
                    singleLine = true,
                    modifier = Modifier.width(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        carritoViewModel.actualizarCantidadProducto(producto.id, cantidad)
                    }
                ) {
                    Text("Actualizar")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { carritoViewModel.eliminarProductoDelCarrito(producto.id) },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
            ) {
                Text("Eliminar del carrito")
            }
        }
    }
}
