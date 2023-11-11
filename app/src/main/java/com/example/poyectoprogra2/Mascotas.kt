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
import com.example.poyectoprogra2.ModeloDatos.Mascota
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme

@Composable
fun Mascotas() {
    var categoriaSeleccionada by remember { mutableStateOf("Perros") }
    val categorias = listOf("Perros", "Gatos")

    val listaPerros = listOf(
        Mascota("Bulldog", "Bulldog Inglés", "$200"),
        Mascota("Beagle", "Beagle", "$150")
        // Agrega más perros aquí
    )

    val listaGatos = listOf(
        Mascota("Siames", "Siamés", "$120"),
        Mascota("Persa", "Persa", "$180")
        // Agrega más gatos aquí
    )

    PoyectoProgra2Theme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Mascotas", style = MaterialTheme.typography.titleLarge)
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

            val listaMascotas = if (categoriaSeleccionada == "Perros") listaPerros else listaGatos

            LazyColumn(contentPadding = PaddingValues(bottom = 56.dp)) {
                items(listaMascotas) { mascota ->
                    MascotaCard(mascota) {
                        // Simula agregar la mascota al carrito
                        println("Agregado al carrito: ${mascota.nombre}")
                    }
                }
            }
        }
    }
}

@Composable
fun MascotaCard(mascota: Mascota, onAddToCartClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val imageResource = painterResource(id = mascotaImagenes[mascota.nombre] ?: R.drawable.placeholder_image)
            Image(
                painter = imageResource,
                contentDescription = "Imagen de ${mascota.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(mascota.nombre, style = MaterialTheme.typography.titleMedium)
            Text(mascota.raza, style = MaterialTheme.typography.bodyMedium)
            Text(mascota.precio, style = MaterialTheme.typography.bodyLarge)
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
fun MascotasPreview() {
    Mascotas()
}

val mascotaImagenes = mapOf(
    "Bulldog" to R.drawable.bulldog,
    "Beagle" to R.drawable.bulldog,
    "Siames" to R.drawable.persa,
    "Persa" to R.drawable.persa
    // Agrega más referencias de imágenes aquí
)
