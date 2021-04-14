package com.fuze.ttapp.api.models

import com.google.gson.annotations.SerializedName

data class TwitterUser (
    @SerializedName("name")
    var userName: String = "",
    @SerializedName("screen_name")
    var screenName: String = "",
    @SerializedName("profile_image_url_https")
    var imageUrl: String = ""
)

