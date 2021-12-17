package com.example.resepmasakanindo_160417065.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.resepmasakanindo_160417065.model.FavoritWithReseps
import com.example.resepmasakanindo_160417065.model.FavoritWithUsers

@Dao
interface FavoritDao {
    @Transaction
    @Query("SELECT * FROM user")
    suspend fun getFavoritWithResep() : List<FavoritWithReseps>

    @Transaction
    @Query("SELECT * FROM resep")
    suspend fun getFavoritWithUser() : List<FavoritWithUsers>
}