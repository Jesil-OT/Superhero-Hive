package com.jesil.toborowei.hive.superherohive.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jesil.toborowei.hive.superherohive.model.objects.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "superhero_hive_table")
data class HeroModel(
    @Embedded
    @SerializedName("appearance")
    val appearance: Appearance,

    @Embedded
    @SerializedName("biography")
    val biography: Biography,

    @Embedded
    @SerializedName("connections")
    val connections: Connections,

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @Embedded
    @SerializedName("images")
    val images: Images,

    @SerializedName("name")
    val name: String,

    @Embedded
    @SerializedName("powerstats")
    val powerStats: Powerstats,

    @Embedded
    @SerializedName("work")
    val work: Work,

    var isFavorite : Boolean = false
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HeroModel

        if (appearance != other.appearance) return false
        if (biography != other.biography) return false
        if (connections != other.connections) return false
        if (id != other.id) return false
        if (images != other.images) return false
        if (name != other.name) return false
        if (powerStats != other.powerStats) return false
        if (work != other.work) return false
        if (isFavorite != other.isFavorite) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}