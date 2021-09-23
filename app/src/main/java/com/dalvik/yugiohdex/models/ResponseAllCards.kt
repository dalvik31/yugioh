package com.dalvik.yugiohdex.models

import com.google.gson.annotations.SerializedName

data class ResponseAllCards(
    @SerializedName("data")
    val allCards : ArrayList<Card>
)
