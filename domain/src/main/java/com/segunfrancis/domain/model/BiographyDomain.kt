package com.segunfrancis.domain.model

data class BiographyDomain(
    val aliases: List<String>,

    val firstAppearance: String,

    val fullName: String,

    val placeOfBirth: String,

    val publisher: String?
)