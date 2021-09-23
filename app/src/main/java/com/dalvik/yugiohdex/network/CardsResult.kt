package com.dalvik.yugiohdex.network

import com.dalvik.yugiohdex.models.Card

sealed class CardsResult{
    data class Success(val data: ArrayList<Card>): CardsResult()
    data class Failure(val data: String): CardsResult()
}
