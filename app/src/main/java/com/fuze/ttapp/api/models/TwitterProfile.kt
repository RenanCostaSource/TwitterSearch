package com.fuze.ttapp.api.models

import com.google.gson.annotations.SerializedName

data class TwitterProfile (
    @SerializedName("name")
    var userName: String? = null,

    @SerializedName("screen_name")
    var screenName: String? = null,


    @SerializedName("profile_image_url_https")
    var imageUrl: String? = null,

    @SerializedName("location")
    var location: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("followers_count")
    var followers: Int? = null,

    @SerializedName("friends_count")
    var following: Int? = null,

    @SerializedName("profile_banner_url")
    var banner: String? = null,
        )
