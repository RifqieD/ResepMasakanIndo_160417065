package com.example.resepmasakanindo_160417065.model

import androidx.room.*

@Entity(tableName = "resep")
data class Resep(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resepId")
    val resepId: Long,
    @ColumnInfo(name = "nama")
    val title:String,
    @ColumnInfo(name = "deskripsi")
    val deskripsi:String,
    @ColumnInfo(name = "gambar")
    var gambar:String,
    @ColumnInfo(name = "kategoriId")
    val kategoriId: Long,
    @ColumnInfo(name = "userId")
    val userId: Long
)

data class KategoriWithReseps(
    @Embedded val kategori: Kategori,
    @Relation(
        parentColumn = "kategoriId",
        entityColumn = "kategoriId"
    )
    val reseps: ArrayList<Resep>
)

data class UserWithReseps(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val reseps: ArrayList<Resep>
)
