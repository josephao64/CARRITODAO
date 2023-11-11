package com.example.poyectoprogra2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.poyectoprogra2.ModeloDatos.ItemCarrito
import com.example.poyectoprogra2.ModeloDatos.Producto
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito
import com.example.poyectoprogra2.db.CarritoDao

@Database(entities = [Producto::class, ProductoCarrito::class, ItemCarrito::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carritoDao(): CarritoDao
    // Puedes tener más DAOs según tus entidades

    // Singleton para evitar múltiples instancias de la base de datos abiertas al mismo tiempo
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nombre_de_tu_base_de_datos"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
