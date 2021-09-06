package com.segunfrancis.domain.model

data class AppearanceDomain(
    val eyeColor: String,

    val gender: String,

    val hairColor: String,

    val height: List<String>,

    val race: String?,

    val weight: List<String>
)