package com.example.poyectoprogra2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.poyectoprogra2.ModeloDatos.Accesorio
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme

@Composable
fun Accesorios() {
    var categoriaSeleccionada by remember { mutableStateOf("Collares") }
    val categorias = listOf("Collares", "Camas")

    val listaCollares = listOf(
        Accesorio("Collar de cuero", "Collar de cuero resistente", "$15"),
        Accesorio("Collar con luz", "Collar con LED para seguridad nocturna", "$20")
        // Agrega más collares aquí
    )

    val listaCamas = listOf(
        Accesorio("Cama ortopédica", "Cama ortopédica para perros", "$40"),
        Accesorio("Cama suave", "Cama suave para gatos", "$30")
        // Agrega más camas aquí
    )

    PoyectoProgra2Theme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Accesorios", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                categorias.forEach { categoria ->
                    Button(
                        onClick = { categoriaSeleccionada = categoria },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (categoria == categoriaSeleccionada) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(categoria)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val listaAccesorios = when (categoriaSeleccionada) {
                "Collares" -> listaCollares
                "Camas" -> listaCamas
                else -> emptyList()
            }
            val bottomPadding = 56.dp
            LazyColumn(contentPadding = PaddingValues(bottom = bottomPadding)) {
                items(listaAccesorios) { accesorio ->
                    AccesorioCard(accesorio) {
                        // Simula agregar el accesorio al carrito
                        println("Agregado al carrito: ${accesorio.nombre}")
                    }
                }
            }
        }
    }
}

@Composable
fun AccesorioCard(accesorio: Accesorio, onAddToCartClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val imageResource = painterResource(id = accesoriosImagenes[accesorio.nombre] ?: R.drawable.placeholder_image)
            Image(
                painter = imageResource,
                contentDescription = "Imagen de ${accesorio.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(accesorio.nombre, style = MaterialTheme.typography.titleMedium)
            Text(accesorio.descripcion, style = MaterialTheme.typography.bodyMedium)
            Text(accesorio.precio, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onAddToCartClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al carrito")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccesoriosPreview() {
    Accesorios()
}

val accesoriosImagenes = mapOf(
    "Collar de cuero" to R.drawable.correa,
    "Collar con luz" to R.drawable.correa,
    // Agrega más referencias de imágenes aquí
    "Cama ortopédica" to R.drawable.cama,
    "Cama suave" to R.drawable.cama
)
