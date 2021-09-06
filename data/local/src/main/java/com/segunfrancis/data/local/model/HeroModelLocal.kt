package com.segunfrancis.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superhero_hive_table")
data class HeroModelLocal(
    @Embedded
    val appearanceLocal: AppearanceLocal,

    @Embedded
    val biographyLocal: BiographyLocal,

    @Embedded
    val connectionsLocal: ConnectionsLocal,

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @Embedded
    val imagesLocal: ImagesLocal,

    val name: String,

    @Embedded
    val powerStats: PowerstatsLocal,

    @Embedded
    val workLocal: WorkLocal,

    var isFavorite : Boolean = false
)
