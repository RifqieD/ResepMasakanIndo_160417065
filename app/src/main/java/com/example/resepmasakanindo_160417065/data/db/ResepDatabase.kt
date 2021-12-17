package com.example.resepmasakanindo_160417065.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.resepmasakanindo_160417065.data.dao.KategoriDao
import com.example.resepmasakanindo_160417065.data.dao.ResepDao
import com.example.resepmasakanindo_160417065.data.dao.UserDao
import com.example.resepmasakanindo_160417065.model.Kategori
import com.example.resepmasakanindo_160417065.model.Resep
import com.example.resepmasakanindo_160417065.model.User

@Database(entities = [User::class, Kategori::class, Resep::class], version = 3)
abstract class ResepDatabase : RoomDatabase() {
    abstract val resepDao: ResepDao
    abstract val userDao: UserDao
    abstract val kategoriDao: KategoriDao

    companion object {

        @Volatile
        private var INSTANCE: ResepDatabase? = null

        fun getInstance(context: Context): ResepDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResepDatabase::class.java,
                        "resep_masakan_indonesia_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}