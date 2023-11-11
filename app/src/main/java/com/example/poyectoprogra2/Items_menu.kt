package com.example.poyectoprogra2
sealed class Items_menu(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object Pantalla1: Items_menu(R.drawable.ic_baseline_house_24, "Inicio", "Pantalla1")
    object Pantalla2: Items_menu(R.drawable.ic_baseline_pets_24, "Mascotas", "Pantalla2")
    object Pantalla3: Items_menu(R.drawable.ic_baseline_fastfood_24, "Alimentos", "Pantalla3")
    object Pantalla4: Items_menu(R.drawable.ic_baseline_stars_24, "Accesorios", "Pantalla4")
    object Pantalla5: Items_menu(R.drawable.ic_baseline_account_circle_24, "Perfil", "Pantalla5")
    object Pantalla6: Items_menu(R.drawable.ic_baseline_shopping_cart_24, "Carrito", "Pantalla6")
}
