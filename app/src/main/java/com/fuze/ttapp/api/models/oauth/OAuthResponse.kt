package com.fuze.ttapp.api.models.oauth

import com.google.gson.annotations.SerializedName

data class OAuthResponse (
    @SerializedName("access_token")
    val accessToken: String
)
