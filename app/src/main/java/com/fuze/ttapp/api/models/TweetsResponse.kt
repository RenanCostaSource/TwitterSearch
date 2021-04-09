package com.fuze.ttapp.api.models

import com.google.gson.annotations.SerializedName

data class TweetsResponse (
    @SerializedName("statuses")
    var tweets: List<Twit>? = null
)
