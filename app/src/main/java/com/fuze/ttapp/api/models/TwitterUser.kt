package com.fuze.ttapp.api.models

import com.google.gson.annotations.SerializedName


data class TwitterUser (

    @SerializedName("name")
    var userName: String? = null,

    @SerializedName("screen_name")
    var screenName: String? = null,


    @SerializedName("profile_image_url_https")
    var imageUrl: String? = null



)
