package com.dalvik.yugiohdex.models

import com.google.gson.annotations.SerializedName

data class CardImages(
    @SerializedName("image_url")
    val image_full :String,

    @SerializedName("image_url_small")
    val image_thumbnail: String
)
