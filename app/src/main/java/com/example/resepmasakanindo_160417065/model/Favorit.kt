package com.example.resepmasakanindo_160417065.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["userId", "resepId"])
data class UserResepCrossRef(
    val userId: Long,
    val resepId: Long
)

data class FavoritWithReseps(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "resepId",
        associateBy = Junction(UserResepCrossRef::class)
    )
    val reseps: List<Resep>
)

data class FavoritWithUsers(
    @Embedded val resep: Resep,
    @Relation(
        parentColumn = "resepId",
        entityColumn = "userId",
        associateBy = Junction(UserResepCrossRef::class)
    )
    val users: List<User>
)