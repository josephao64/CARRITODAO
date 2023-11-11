package com.example.poyectoprogra2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PoyectoProgra2Theme {
                val navController = rememberNavController()
                val userFlow = remember { Firebase.auth.currentUserFlow() }
                val user by userFlow.collectAsState(initial = Firebase.auth.currentUser)

                LaunchedEffect(user) {
                    val userId = user?.uid ?: ""
                    if (user != null) {
                        navController.navigate("pantalla_principal/$userId") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        navController.navigate("login") {
                            popUpTo("pantalla_principal") { inclusive = true }
                        }
                    }
                }

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("pantalla_principal/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId") ?: ""
                        PantallaPrincipal(navController, userId)
                    }
                }
            }
        }
    }
}

// Extensión para obtener un StateFlow del estado de autenticación actual
fun FirebaseAuth.currentUserFlow() = callbackFlow {
    val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        this.trySend(auth.currentUser).isSuccess
    }
    addAuthStateListener(authStateListener)
    awaitClose { removeAuthStateListener(authStateListener) }
}
