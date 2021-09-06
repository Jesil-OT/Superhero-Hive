package com.segunfrancis.data.remote.model

import com.google.gson.annotations.SerializedName

data class ConnectionsRemote(
    @SerializedName("groupAffiliation")
    val groupAffiliation: String,

    @SerializedName("relatives")
    val relatives: String
)