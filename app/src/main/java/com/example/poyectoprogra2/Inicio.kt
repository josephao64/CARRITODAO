package com.example.poyectoprogra2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Inicio() {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    PoyectoProgra2Theme {
        // Aquí puedes agregar un Scaffold si necesitas una AppBar o un Drawer
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Banner Promocional
            item { BannerPromocional() }

            // Categorías de Acceso Rápido
            item { CategoriasAccesoRapido() }

            // Barra de Búsqueda
            item {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Buscar productos") },
                    trailingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            performSearch(searchText)
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Productos Destacados

        }
    }
}

// Asume que esta función realizará la búsqueda real en tus datos
fun performSearch(query: String) {
    // Implementa la lógica de búsqueda aquí
    // Podrías llamar a un ViewModel, o directamente a una API para obtener resultados
}

@Composable
fun BannerPromocional() {
    // Asumiendo que tendrás un carrusel de imágenes
    Box(
        modifier = Modifier
            .fillMaxWidth() // Hace que el Box ocupe todo el ancho de la pantalla
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        // Aquí usarías un Pager o algo similar para el carrusel de imágenes
        Image(
            painter = painterResource(id = R.drawable.banner_placeholder),
            contentDescription = "Banner Promocional",
            contentScale = ContentScale.Crop // Escala la imagen para que llene el espacio sin distorsionarla
        )
    }
}

@Composable
fun CategoriasAccesoRapido() {
    // Simulando algunas categorías
    val categorias = listOf("Perros", "Gatos", "Aves")

    Column {
        Text("Categorías de Acceso Rápido", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            categorias.forEach { categoria ->
                Button(onClick = { /* Navegar a la categoría */ }) {
                    Text(categoria)
                }
            }
        }
    }
}
@Composable
fun ProductosDestacados() {
    // Aquí iría tu lógica para obtener los productos destacados
    // Asumiremos que tenemos una lista de productos destacados
    val productosDestacados = listOf(
        // Reemplaza esto con tus datos de productos
        "Producto 1",
        "Producto 2",
        "Producto 3"
    )

    Column {
        Text("Productos Destacados", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(productosDestacados) { producto ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        // Supongamos que tienes un drawable para cada producto
                        Image(
                            painter = painterResource(id = R.drawable.placeholder_image),
                            contentDescription = "Imagen del producto $producto",
                            modifier = Modifier.size(88.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(producto, style = MaterialTheme.typography.titleMedium)
                            // Añade más detalles de acuerdo a tus datos
                        }
                    }
                }
            }
        }
    }
}


// Las siguientes funciones serían similares a `CategoriasAccesoRapido`, adaptadas para cada sección.
// Implementa las demás secciones con lógicas similares a las que se mostraron aquí.

