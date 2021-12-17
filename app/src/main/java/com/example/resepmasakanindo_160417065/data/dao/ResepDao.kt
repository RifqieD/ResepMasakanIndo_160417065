package com.example.resepmasakanindo_160417065.data.dao

import androidx.room.*
import com.example.resepmasakanindo_160417065.model.Resep

@Dao
interface ResepDao {
    @Query("SELECT * FROM resep")
    suspend fun selectAllResep(): List<Resep>

    @Query("SELECT * FROM resep WHERE resepId = :resepId")
    suspend fun findResep(resepId: Long): Resep

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResep(resep: Resep)

    @Delete
    suspend fun deleteResep(resep: Resep)
}