package com.example.poyectoprogra2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun Perfil() {
    val user = Firebase.auth.currentUser
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_user_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.secondary)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (user != null) {
            Text(
                text = "¡Bienvenido, ${user.displayName}!",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    Firebase.auth.signOut()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Cerrar sesión")
            }
        } else {
            Text(
                text = "Inicia sesión para ver tu perfil",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
