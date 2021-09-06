package com.segunfrancis.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superhero_hive_table")
data class HeroModel(
    @Embedded
    val appearance: Appearance,

    @Embedded
    val biography: Biography,

    @Embedded
    val connections: Connections,

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @Embedded
    val images: Images,

    val name: String,

    @Embedded
    val powerStats: Powerstats,

    @Embedded
    val work: Work,

    var isFavorite : Boolean = false
)
