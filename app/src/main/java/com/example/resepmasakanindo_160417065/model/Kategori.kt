package com.example.resepmasakanindo_160417065.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kategoriId")
    val kategoriId: Long,
    @ColumnInfo(name = "nama")
    val nama: String
)
