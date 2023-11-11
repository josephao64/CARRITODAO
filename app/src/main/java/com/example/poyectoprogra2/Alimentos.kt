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
import com.example.poyectoprogra2.ModeloDatos.Alimento
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme

@Composable
fun Alimentos() {
    var categoriaSeleccionada by remember { mutableStateOf("Seco") }
    val categorias = listOf("Seco", "Húmedo")

    val listaSecos = listOf(
        Alimento("Croquetas para perro", "Alimento seco balanceado", "$25"),
        Alimento("Croquetas para gato", "Alimento seco premium para gatos", "$30")
        // Agrega más alimentos secos aquí
    )

    val listaHumeda = listOf(
        Alimento("Lata de pollo", "Alimento húmedo para perro sabor pollo", "$2"),
        Alimento("Lata de atún", "Alimento húmedo para gato sabor atún", "$2.5")
        // Agrega más alimentos húmedos aquí
    )

    PoyectoProgra2Theme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Alimentos", style = MaterialTheme.typography.titleLarge)
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

            val listaAlimentos = when (categoriaSeleccionada) {
                "Seco" -> listaSecos
                "Húmedo" -> listaHumeda
                else -> emptyList()
            }

            LazyColumn(contentPadding = PaddingValues(bottom = 56.dp)) {
                items(listaAlimentos) { alimento ->
                    AlimentoCard(alimento) {
                        // Simula agregar el alimento al carrito
                        println("Agregado al carrito: ${alimento.nombre}")
                    }
                }
            }
        }
    }
}

@Composable
fun AlimentoCard(alimento: Alimento, onAddToCartClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val imageResource = painterResource(id = alimentosImagenes[alimento.nombre] ?: R.drawable.placeholder_image)
            Image(
                painter = imageResource,
                contentDescription = "Imagen de ${alimento.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(alimento.nombre, style = MaterialTheme.typography.titleMedium)
            Text(alimento.descripcion, style = MaterialTheme.typography.bodyMedium)
            Text(alimento.precio, style = MaterialTheme.typography.bodyLarge)
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
fun AlimentosPreview() {
    Alimentos()
}

val alimentosImagenes = mapOf(
    "Croquetas para perro" to R.drawable.croquetas_perro,
    "Croquetas para gato" to R.drawable.croquetas_gato,
    "Lata de pollo" to R.drawable.lata_pollo,
    "Lata de atún" to R.drawable.lata_atun
    // Agrega más referencias de imágenes aquí
)
