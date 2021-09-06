package com.segunfrancis.data.local.model

data class BiographyLocal(
    val aliases: List<String>,

    val firstAppearance: String,

    val fullName: String,

    val placeOfBirth: String,

    val publisher: String?
)