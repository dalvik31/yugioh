package com.dalvik.yugiohdex.network

import com.dalvik.yugiohdex.models.ResponseAllCards
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cardinfo.php?&startdate=01/01/2000&enddate=05/28/2003")
    suspend fun getAllCards() : Response<ResponseAllCards>
}