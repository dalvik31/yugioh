package com.dalvik.yugiohdex.models

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("race")
    val race: String,
    @SerializedName("atk")
    val attack: Int,
    @SerializedName("def")
    val defence: Int,
    @SerializedName("level")
    val level: Int,
    @SerializedName("attribute")
    val attribute: String,
    @SerializedName("card_images")
    val images: ArrayList<CardImages>,
    @SerializedName("desc")
    val description: String
) {

    fun getThumbnail(): String {
        return if (images.size > 0 && images[0].image_thumbnail.isNotEmpty()) images[0].image_thumbnail else "https://m.media-amazon.com/images/I/41zZvOzBv3L._AC_SY355_.jpg"
    }

    fun getFullImage(): String {
        return if (images.size > 0 && images[0].image_full.isNotEmpty()) images[0].image_full else "https://m.media-amazon.com/images/I/41zZvOzBv3L._AC_SY355_.jpg"
    }
}
