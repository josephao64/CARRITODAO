package com.example.poyectoprogra2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme
import com.google.firebase.auth.FirebaseAuth

class Pantalla : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PoyectoProgra2Theme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaPrincipal(navController, userId)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(navController: NavController, userId: String) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    NavigationHost(navController, userId)
    val navigationItems = listOf(
        Items_menu.Pantalla1,
        Items_menu.Pantalla2,
        Items_menu.Pantalla3,
        Items_menu.Pantalla4,
        Items_menu.Pantalla5,
        Items_menu.Pantalla6
    )
    Scaffold(
        bottomBar = { NavegacionInferior(navController, navigationItems) },

        ) {
        NavigationHost(navController, userId)
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@Composable
fun NavegacionInferior(
    navController: NavController,
    menuItems: List<Items_menu>
) {
    BottomAppBar() {
        BottomNavigation() {
            val currentRoute = currentRoute(navController)
            menuItems.forEach { item->
                BottomNavigationItem(
                    selected = currentRoute == item.ruta,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    alwaysShowLabel = false
                )
            }
        }
    }

}

