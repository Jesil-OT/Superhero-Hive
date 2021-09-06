package com.segunfrancis.data.local.model

data class AppearanceLocal(
    val eyeColor: String,

    val gender: String,

    val hairColor: String,

    val height: List<String>,

    val race: String?,

    val weight: List<String>
)