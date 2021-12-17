package com.example.resepmasakanindo_160417065.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user", indices = [Index(
        value = ["username"],
        unique = true
    )]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Long,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "password")
    var password: String? = null
)
