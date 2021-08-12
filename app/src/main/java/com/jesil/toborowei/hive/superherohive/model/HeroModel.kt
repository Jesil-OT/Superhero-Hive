package com.jesil.toborowei.hive.superherohive.model

import android.os.Parcelable
import com.bumptech.glide.load.resource.bitmap.VideoDecoder.parcel
import com.jesil.toborowei.hive.superherohive.model.objects.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroModel(
    val appearance: Appearance,
    val biography: Biography,
    val connections: Connections,
    val id: Int,
    val images: Images,
    val name: String,
    val powerstats: Powerstats,
    val work: Work
) : Parcelable