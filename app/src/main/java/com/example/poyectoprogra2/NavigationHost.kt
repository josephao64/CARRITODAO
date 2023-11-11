package com.example.poyectoprogra2

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.poyectoprogra2.Items_menu.*
import com.google.firebase.auth.FirebaseAuth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.menu.Items_menu.*
@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Pantalla1.ruta,
    ){
        composable(Pantalla1.ruta){
            Inicio()
        }
        composable(Pantalla2.ruta){
            Mascotas()
        }
        composable(Pantalla3.ruta){
            Alimentos()
        }
        composable(Pantalla4.ruta){
            Accesorios()
        }
        composable(Pantalla5.ruta){
            CarritoPantalla()
        }
        composable(Pantalla6.ruta){
            Perfil()
        }
    }
}
