package com.fuze.ttapp.api.models

import com.google.gson.annotations.SerializedName

data class Twit (
    @SerializedName("user")
    var user: TwitterUser? = null,
    @SerializedName("text")
    var body: String? = null
)
