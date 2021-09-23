package com.dalvik.yugiohdex.repository

import androidx.lifecycle.MutableLiveData
import com.dalvik.yugiohdex.models.Card
import com.dalvik.yugiohdex.network.CardsResult
import com.dalvik.yugiohdex.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepository {

    companion object{
        private var INSTANCE: CardRepository?= null
        fun getInstance() = INSTANCE?: CardRepository().also {
            INSTANCE = it
        }
    }

    suspend fun getAllCardsRetrofit(): CardsResult  = withContext(Dispatchers.IO) {
        try{
            val response = RetrofitBuilder.apiService.getAllCards()
            if(response.isSuccessful && response.body()!= null)
                return@withContext CardsResult.Success(response.body()!!.allCards)
            else
                return@withContext CardsResult.Failure("No hay cartas")
        }catch (e: Exception){
            return@withContext CardsResult.Failure(e.message.toString())
        }
    }

}