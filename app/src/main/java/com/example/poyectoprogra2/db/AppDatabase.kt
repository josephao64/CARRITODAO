package com.example.poyectoprogra2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito

@Database(entities = [ProductoCarrito::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carritoDao(): CarritoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Si la instancia no es nula, la devuelve,
            // si es nula, crea la base de datos
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nombre_de_tu_base_de_datos"
                )
                    // Puedes agregar aquí configuraciones adicionales de la base de datos
                    .build()
                INSTANCE = instance
                // Retorna la instancia creada
                instance
            }
        }
    }
}
