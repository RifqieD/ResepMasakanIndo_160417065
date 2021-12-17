package com.example.resepmasakanindo_160417065.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.resepmasakanindo_160417065.model.Kategori

@Dao
interface KategoriDao {
    @Query("SELECT * FROM kategori")
    suspend fun selectAllKategori(): List<Kategori>

    @Query("SELECT * FROM kategori WHERE kategoriId = :kategoriId")
    suspend fun findKategori(kategoriId: Long): Kategori
}